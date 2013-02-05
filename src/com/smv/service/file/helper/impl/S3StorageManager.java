package com.smv.service.file.helper.impl;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.amazon.s3.ListEntry;
import com.smv.service.file.helper.IStorageManager;
import com.smv.util.vendor.s3.AwsManager;

public class S3StorageManager implements IStorageManager {
	private static final Logger LOGGER = Logger.getLogger(S3StorageManager.class);
	
	@Override
	public void createBucket(String bucketName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ListEntry> listFiles(String bucket, String regex) {
		AwsManager awsManager = AwsManager.getInstance();
		List<ListEntry> entriesList = null;
		
		try {
			entriesList = awsManager.getFiles(bucket, regex, null, null, null);
			
			for(ListEntry entry : entriesList) {
	    		LOGGER.info("Key : " + entry.key + 
	    				    ", eTag : " + entry.eTag + 
	    				    ", size : " + entry.size + 
	    				    ", StorageClass : " + entry.storageClass + 
	    				    ", LastModified : " + entry.lastModified + 
	    				    ", ownerId : " + entry.owner.id + 
	    				    ", ownerDisplayName : " + entry.owner.displayName);
	    	}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entriesList;
	}

}
