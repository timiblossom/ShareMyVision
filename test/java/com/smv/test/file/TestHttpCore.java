package com.smv.test.file;



import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.Socket;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpClientConnection;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
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

import com.smv.util.http.HttpClient;
import com.smv.util.io.StreamUtil;

/**
 * Elemental example for executing a POST request.
 * <p>
 * Please note the purpose of this application is demonstrate the usage of HttpCore APIs.
 * It is NOT intended to demonstrate the most efficient way of building an HTTP client. 
 *
 *
 *
 * <!-- empty lines above to avoid 'svn diff' context problems -->
 * @version $Revision$
 */
public class TestHttpCore {

    public static void main1(String[] args) throws Exception {
        
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, "UTF-8");
        HttpProtocolParams.setUserAgent(params, "SMV Android/1.1");
        HttpProtocolParams.setUseExpectContinue(params, true);
        //params.setLongParameter("Content-Length", getFileSize());
        
        BasicHttpProcessor httpproc = new BasicHttpProcessor();
        // Required protocol interceptors
        httpproc.addInterceptor(new RequestContent());
        httpproc.addInterceptor(new RequestTargetHost());
        // Recommended protocol interceptors
        httpproc.addInterceptor(new RequestConnControl());
        httpproc.addInterceptor(new RequestUserAgent());
        httpproc.addInterceptor(new RequestExpectContinue());
        
        HttpRequestExecutor httpexecutor = new HttpRequestExecutor();

        HttpContext context = new BasicHttpContext(null);
        
        HttpHost host = new HttpHost("s3.amazonaws.com", 80);
        
        DefaultHttpClientConnection conn = new DefaultHttpClientConnection();
        ConnectionReuseStrategy connStrategy = new DefaultConnectionReuseStrategy();

        context.setAttribute(ExecutionContext.HTTP_CONNECTION, conn);
        context.setAttribute(ExecutionContext.HTTP_TARGET_HOST, host);

        try {
            
            HttpEntity[] requestBodies = { new ByteArrayEntity(StreamUtil.inputStreamToByteArray(getInputStream())),
            		new ByteArrayEntity(StreamUtil.inputStreamToByteArray(getInputStream1()))};
            //http://s3.amazonaws.com/img10.sharemyvision.com/minh/colosseum.jpg?AWSAccessKeyId=0MP9F4G25D5XT2V8V082&Expires=1286901286&Signature=lgaS%2B04GpbA9nJ8%2FOn%2BuhKozc6Q%3D

            BasicHttpEntityEnclosingRequest[] requests = {new BasicHttpEntityEnclosingRequest("PUT", "/img10.sharemyvision.com/minh/FriendsWithKids.jpg?AWSAccessKeyId=0MP9F4G25D5XT2V8V082&Expires=1286899501&Signature=p7gG56kY1k%2F%2FXn961Mk%2F71ZkCts%3D"),
            		                                      new BasicHttpEntityEnclosingRequest("PUT", "/img10.sharemyvision.com/minh/colosseum.jpg?AWSAccessKeyId=0MP9F4G25D5XT2V8V082&Expires=1286901286&Signature=lgaS%2B04GpbA9nJ8%2FOn%2BuhKozc6Q%3D")};
            
            for (int i = 0; i < requestBodies.length; i++) {
                if (!conn.isOpen()) {
                    Socket socket = new Socket(host.getHostName(), host.getPort());
                    conn.bind(socket, params);
                }
                BasicHttpEntityEnclosingRequest request = //new BasicHttpEntityEnclosingRequest("PUT", "/img10.sharemyvision.com/minh/FriendsWithKids.jpg?AWSAccessKeyId=0MP9F4G25D5XT2V8V082&Expires=1286899501&Signature=p7gG56kY1k%2F%2FXn961Mk%2F71ZkCts%3D");
                request = requests[i];	
                request.setEntity(requestBodies[i]);
                System.out.println(">> Request URI: " + request.getRequestLine().getUri());

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
                } else {
                    System.out.println("Connection kept alive...");
                }
            }
        } finally {
            conn.close();
        }        
                        
    }
    
    public static void main(String[] args) throws Exception {
    	HttpClient.httpPut("http://s3.amazonaws.com/img10.sharemyvision.com/minh/colosseum.jpg?AWSAccessKeyId=0MP9F4G25D5XT2V8V082&Expires=1286908715&Signature=kJmSMwOVuEKw9b4sYHqIpaK3tpU%3D", null, getInputStream1());
    }
    
    private static InputStream getInputStream() throws FileNotFoundException {
    	File file = new File("C:\\Dev\\resources\\FriendsWithKids.jpg");
    	InputStream is = new BufferedInputStream (new FileInputStream (file));
    	return is;
    }
    
    private static InputStream getInputStream1() throws FileNotFoundException {
    	File file = new File("C:\\Dev\\resources\\colosseum.jpg");
    	InputStream is = new BufferedInputStream (new FileInputStream (file));
    	return is;
    }
    
    private static long getFileSize() {
    	File file = new File("C:\\Dev\\resources\\FriendsWithKids.jpg");
    	return file.length();
    }
}
