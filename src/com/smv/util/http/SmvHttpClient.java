package com.smv.util.http;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SmvHttpClient {
	private static final Log log = LogFactory.getLog(SmvHttpClient.class);


	
	public static SmvHttpResponse post(String url, Map<String, String> headers, Map<String, String> data) {
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(url);

		if (headers != null) {
			for(Map.Entry<String, String> entry : headers.entrySet()) {
				post.setRequestHeader(entry.getKey(), entry.getValue());
			}
		}

		if (data != null) {
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			for(Map.Entry<String, String> entry : data.entrySet()) {
				pairs.add(new NameValuePair(entry.getKey(), entry.getValue()));
			}

			post.setRequestBody(pairs.toArray(new NameValuePair[0]));
		} else {
			//TODO: will resolve the deprecation later
			post.setRequestBody("");
		}

		return processPost(post);
	}

	public static SmvHttpResponse post(String url, Map<String, String> headers, String data) {

		PostMethod post = new PostMethod(url);

		if (headers != null) {
			for(Map.Entry<String, String> entry : headers.entrySet()) {
				post.setRequestHeader(entry.getKey(), entry.getValue());
			}
		}


		//TODO: will resolve the deprecation later
		if (data != null) {
			post.setRequestBody(data);
		} else {
			post.setRequestBody("");
		}

		return processPost(post);
	}


	public static SmvHttpResponse get(String url, Map<String, String> headers) {
		GetMethod get = new GetMethod(url);

		get.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
				new DefaultHttpMethodRetryHandler(3, false));


		if (headers != null) {
			for(Map.Entry<String, String> entry : headers.entrySet()) {
				get.setRequestHeader(entry.getKey(), entry.getValue());
			}
		}

		try {

			HttpClient client = new HttpClient();
			int statusCode = client.executeMethod(get);

			if (statusCode != HttpStatus.SC_OK && statusCode != HttpStatus.SC_CREATED) {
				log.error("Method failed: " + get.getStatusLine());
				return null;
			}

			
			Header[] resHeaders = (Header[]) get.getResponseHeaders();

			SmvHttpResponse response = new SmvHttpResponse();
			for(Header header : resHeaders) {
				response.headers.put(header.getName(), header.getValue());
			}

			
			byte[] responseBody;

			responseBody = get.getResponseBody();
			response.body = new String(responseBody);
			
			return response;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	private static SmvHttpResponse processPost(PostMethod post) {
		try {
			HttpClient client = new HttpClient();
			post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
					new DefaultHttpMethodRetryHandler(3, false));
			
			int statusCode = client.executeMethod(post);

			if (statusCode != HttpStatus.SC_OK && statusCode != HttpStatus.SC_CREATED) {
				log.error("Method failed: " + post.getStatusLine());
				return null;
			}

			SmvHttpResponse response = new SmvHttpResponse();
			Header[] resHeaders = (Header[]) post.getResponseHeaders();

			for(Header header : resHeaders) {
				response.headers.put(header.getName(), header.getValue());
			}

			InputStream is = post.getResponseBodyAsStream();
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}

			response.body = sb.toString();

			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static class SmvHttpResponse {
		public Map<String, String> headers;
		public String body;

		public SmvHttpResponse() {
			headers = new HashMap<String, String>();
		}

		public String getCookies() {
			return headers.get("Set-Cookie");
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			if (headers != null) {
				for(Map.Entry<String, String> entry : headers.entrySet()) {
					sb.append("Header : " + entry.getKey() + " : " + entry.getValue() + "\n");
				}
			}

			sb.append("Body : " + body);

			return sb.toString();
		}
	}



}
