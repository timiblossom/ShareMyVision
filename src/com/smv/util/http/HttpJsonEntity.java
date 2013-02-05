package com.smv.util.http;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.message.BasicHeader;

public class HttpJsonEntity extends HttpEntityWrapper {
	private static Header JSON_CONTENT_TYPE = new BasicHeader("Content-Type", "application/json; charset=utf-8");
	public HttpJsonEntity(HttpEntity wrapped) {
		super(wrapped);
	}

	@Override
    public Header getContentType() {
	   return JSON_CONTENT_TYPE;
	}
}
