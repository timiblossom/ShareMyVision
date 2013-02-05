package com.smv.util.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Map;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpClientConnection;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestExpectContinue;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.apache.http.util.EntityUtils;

import com.smv.util.io.StreamUtil;

public class HttpClient {


	public static HttpResponse httpGet(String url, Map<String, String> headers) throws UnknownHostException, IOException, HttpException {
		URL aURL = new URL(url);

		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, "UTF-8");
		HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1");
		HttpProtocolParams.setUseExpectContinue(params, true);

		BasicHttpProcessor httpproc = new BasicHttpProcessor();
		// Required protocol interceptors
		httpproc.addInterceptor(new CustomRequestContent(headers));
		httpproc.addInterceptor(new RequestTargetHost());
		// Recommended protocol interceptors
		httpproc.addInterceptor(new RequestConnControl());
		httpproc.addInterceptor(new RequestUserAgent());
		httpproc.addInterceptor(new RequestExpectContinue());

		HttpRequestExecutor httpexecutor = new HttpRequestExecutor();

		HttpContext context = new BasicHttpContext(null);
		HttpHost host = new HttpHost(aURL.getHost(), aURL.getPort()==-1? 80 : aURL.getPort());

		DefaultHttpClientConnection conn = new DefaultHttpClientConnection();
		ConnectionReuseStrategy connStrategy = new DefaultConnectionReuseStrategy();

		context.setAttribute(ExecutionContext.HTTP_CONNECTION, conn);
		context.setAttribute(ExecutionContext.HTTP_TARGET_HOST, host);

		try {

			if (!conn.isOpen()) {
				Socket socket = new Socket(host.getHostName(), host.getPort());
				conn.bind(socket, params);
			}
			BasicHttpRequest request = new BasicHttpRequest("GET", aURL.getPath());


			request.setParams(params);
			httpexecutor.preProcess(request, httpproc, context);
			HttpResponse response = httpexecutor.execute(request, conn, context);
			response.setParams(params);
			httpexecutor.postProcess(response, httpproc, context);


			//System.out.println("<< Response: " + response.getStatusLine());
			//System.out.println(EntityUtils.toString(response.getEntity()));
			//System.out.println("==============");
			if (!connStrategy.keepAlive(response, context)) {
				conn.close();
			} //TODO: will use keep-alive to optimize

			return response;
		} finally {
			conn.close();
		}
	}

	public static HttpResponse httpPost(String url, Map<String, String> headers, String data) throws IOException, HttpException {
		URL aURL = new URL(url);

		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, "UTF-8");
		HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1");
		HttpProtocolParams.setUseExpectContinue(params, true);

		BasicHttpProcessor httpproc = new BasicHttpProcessor();
		// Required protocol interceptors
		httpproc.addInterceptor(new CustomRequestContent(headers));
		httpproc.addInterceptor(new RequestTargetHost());
		// Recommended protocol interceptors
		httpproc.addInterceptor(new RequestConnControl());
		httpproc.addInterceptor(new RequestUserAgent());
		httpproc.addInterceptor(new RequestExpectContinue());

		HttpRequestExecutor httpexecutor = new HttpRequestExecutor();

		HttpContext context = new BasicHttpContext(null);

		HttpHost host = new HttpHost(aURL.getHost(), aURL.getPort()==-1? 80 : aURL.getPort());

		DefaultHttpClientConnection conn = new DefaultHttpClientConnection();
		ConnectionReuseStrategy connStrategy = new DefaultConnectionReuseStrategy();

		context.setAttribute(ExecutionContext.HTTP_CONNECTION, conn);
		context.setAttribute(ExecutionContext.HTTP_TARGET_HOST, host);

		try {
			HttpEntity requestBody = new StringEntity(data);
			String contentType = headers.get("Content-Type");
			if (contentType.startsWith("application/json")) {
				requestBody = new HttpJsonEntity(requestBody);   //new ByteArrayEntity(data.getBytes("UTF-8")));
			}

			if (!conn.isOpen()) {
				Socket socket = new Socket(host.getHostName(), host.getPort());
				conn.bind(socket, params);
			}
			BasicHttpEntityEnclosingRequest request = new BasicHttpEntityEnclosingRequest("POST", aURL.getPath());
			request.setEntity(requestBody);

			request.setParams(params);
			httpexecutor.preProcess(request, httpproc, context);
			HttpResponse response = httpexecutor.execute(request, conn, context);
			response.setParams(params);
			httpexecutor.postProcess(response, httpproc, context);

			//System.out.println("<< Response: " + response.getStatusLine());
			//System.out.println(EntityUtils.toString(response.getEntity()));
			//System.out.println("==============");
			if (!connStrategy.keepAlive(response, context)) {
				conn.close();
			} 

			return response;
		} finally {
			conn.close();
		}        
	}


	public static HttpResponse httpPut(String url, Map<String, String> headers, InputStream in) throws IOException, HttpException {
		return httpPut(url, headers, StreamUtil.inputStreamToByteArray(in));

	}
	
	
	public static HttpResponse httpPut(String url, Map<String, String> headers, byte[] data) throws IOException, HttpException {
		URL aURL = new URL(url);
		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, "UTF-8");
		HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1");
		HttpProtocolParams.setUseExpectContinue(params, true);
		//params.setLongParameter("Content-Length", getFileSize());

		BasicHttpProcessor httpproc = new BasicHttpProcessor();
		// Required protocol interceptors
		httpproc.addInterceptor(new CustomRequestContent(headers));
		httpproc.addInterceptor(new RequestTargetHost());
		// Recommended protocol interceptors
		httpproc.addInterceptor(new RequestConnControl());
		httpproc.addInterceptor(new RequestUserAgent());
		httpproc.addInterceptor(new RequestExpectContinue());

		HttpRequestExecutor httpexecutor = new HttpRequestExecutor();

		HttpContext context = new BasicHttpContext(null);

		HttpHost host = new HttpHost(aURL.getHost(), aURL.getPort()==-1? 80 : aURL.getPort());

		DefaultHttpClientConnection conn = new DefaultHttpClientConnection();
		ConnectionReuseStrategy connStrategy = new DefaultConnectionReuseStrategy();

		context.setAttribute(ExecutionContext.HTTP_CONNECTION, conn);
		context.setAttribute(ExecutionContext.HTTP_TARGET_HOST, host);

		try {

			HttpEntity requestBody = new ByteArrayEntity(data);

			BasicHttpEntityEnclosingRequest request = new BasicHttpEntityEnclosingRequest("PUT", aURL.getFile());                               
			request.setEntity(requestBody);
			
			if (!conn.isOpen()) {
				Socket socket = new Socket(host.getHostName(), host.getPort());
				conn.bind(socket, params);
			}
			
			//params.setLongParameter("Content-Length", getFileSize());
			//params.setParameter("Content-Type", "image/jpeg");
			//params.removeParameter("Transfer-Encoding");
			request.setParams(params);

			httpexecutor.preProcess(request, httpproc, context);
			HttpResponse response = httpexecutor.execute(request, conn, context);
			//response.setParams(params);
			httpexecutor.postProcess(response, httpproc, context);

			System.out.println("<< Response: " + response.getStatusLine());
			System.out.println(EntityUtils.toString(response.getEntity()));
			System.out.println("==============");
			if (!connStrategy.keepAlive(response, context)) {
				conn.close();
			}
			return response;

		} finally {
			conn.shutdown();
			conn.close();
		}        

	}

	private static String getHostFromUrl(String url) {
		if (url == null) {
			return null;			
		} if (url.startsWith("http")) {
			int index = url.indexOf("://");
			url = url.substring(index + 3, url.length());
			index = url.indexOf('/');
			url = url.substring(0, index == -1? url.length() : index);
			return url;
		}

		return null;
	}

	public static void main(String[] args) throws UnknownHostException, IOException, HttpException {
		//System.out.println(EntityUtils.toString(httpGet("http://google.com", null).getEntity()));
		//String data = "{\"action\":\"suu\",\"eventCode\":\"1abc1\",\"userId\":1,\"items\":[{\"path\":\"d2010912\",\"fileName\":\"colosseum.jpg\",\"userId\":1,\"fileSize\":69189}]}";
		//Map<String, String> headers = new HashMap<String, String>();
		//headers.put("Content-Type", "application/json; charset=utf-8");

		//System.out.println(EntityUtils.toString(httpPost("http://sharemyvision.com/web/mo/filerequest", headers, data).getEntity()));
		//System.out.println(getHostFromUrl("http://s3.amazonaws.com/img10.sharemyvision.com/minh/colosseum.jpg?AWSAccessKeyId=0MP9F4G25D5XT2V8V082&Expires=1286901286&Signature=lgaS%2B04GpbA9nJ8%2FOn%2BuhKozc6Q%3D"));

		
		URL aURL = new URL("http://s3.amazonaws.com/img10.sharemyvision.com/minh/colosseum.jpg?AWSAccessKeyId=0MP9F4G25D5XT2V8V082&Expires=1286901286&Signature=lgaS%2B04GpbA9nJ8%2FOn%2BuhKozc6Q%3D");
		System.out.println("protocol = " + aURL.getProtocol());
	    System.out.println("host = " + aURL.getHost());
	    System.out.println("filename = " + aURL.getFile());
	    System.out.println("port = " + aURL.getPort());
	    System.out.println("ref = " + aURL.getRef());
	    System.out.println("path = " + aURL.getPath());
		

	}
}
