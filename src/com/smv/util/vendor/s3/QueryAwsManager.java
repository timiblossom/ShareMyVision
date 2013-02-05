package com.smv.util.vendor.s3;

import com.amazon.s3.QueryStringAuthGenerator;


/**
 * @author Minh Do
 * 03/01/2010
 */
public class QueryAwsManager {
	private QueryStringAuthGenerator generator = null;
	private static QueryAwsManager instance = new QueryAwsManager();
	
	private QueryAwsManager() {
		init();
	}
	
	private void init() {
		generator = new QueryStringAuthGenerator(ConfigBean.S3_ACCESS_KEY_ID, ConfigBean.S3_SECRET_ACCESS_KEY, false);
	}
	
	public static QueryAwsManager getInstance() {
		return instance;
	}

	public QueryStringAuthGenerator getGenerator() {
		return generator;
	}

	public void setGenerator(QueryStringAuthGenerator generator) {
		this.generator = generator;
	}
	
	public static void main(String[] args) {
		QueryAwsManager query = QueryAwsManager.getInstance();
		
		String url = query.getGenerator().createBucket("bbbb", null);
		//String url = query.getGenerator().deleteBucket("aaaa", null);
		System.out.println(url);
		
		

	}
}
