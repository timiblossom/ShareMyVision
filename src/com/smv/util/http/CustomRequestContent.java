package com.smv.util.http;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.RequestContent;

public class CustomRequestContent extends RequestContent {
	private Map<String, String> headers;

	public CustomRequestContent() {
		super();
	}

	public CustomRequestContent(Map<String, String> headers) {
		this.headers = headers;
	}
	public void process(final HttpRequest request, final HttpContext context)  throws HttpException, IOException {
		super.process(request, context);
		if (headers != null) {
			Iterator iter = headers.entrySet().iterator();

			while (iter.hasNext()) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next();
				request.addHeader(entry.getKey(), entry.getValue());
			}
		}
	}

}
