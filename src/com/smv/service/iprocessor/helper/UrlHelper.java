package com.smv.service.iprocessor.helper;

public class UrlHelper {
	public static String[] getPathAndFileName(String uploadUrlStr) {
		String[] retVal = new String[2];
		
		retVal[0] = uploadUrlStr.substring(7);
		
		retVal[1] = retVal[0].substring(retVal[0].lastIndexOf('/') + 1, retVal[0].lastIndexOf('?'));
		retVal[0] = retVal[0].substring(0, retVal[0].lastIndexOf('/'));
		
		return retVal;
	}
	
	public static void main(String[] args)  {
		//System.out.println(generateGuid("192.168.0.1", "10.10.31.85", 10));
		//System.out.println(generateFolderName(1L));
		
		String[] values = getPathAndFileName("http://img1.sharemyvision.com/abc/d2010826/colosseum.jpg?AWSAccessKeyId=0MP9F4G25D5XT2V8V082&Expires=1285551215&Signature=qt6Wq0c48AYmSXIMrRBwYcq2cf0%3D");
		System.out.println("0 : " + values[0]);
		System.out.println("1 : " + values[1]);
				
	}
}
