package com.smv.service.file.helper;

import java.util.List;

import com.amazon.s3.ListEntry;


/**
 * @author Minh Do
 * 03/01/2010
 */
public interface IStorageManager {
	
	public void createBucket(String bucketName);
	public List<ListEntry> listFiles(String bucket, String regex);
	
}
