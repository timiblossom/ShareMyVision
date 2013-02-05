package com.smv.test.s3;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.amazon.s3.Bucket;
import com.amazon.s3.ListEntry;
import com.smv.util.vendor.s3.AwsManager;

public class TestS3Client {
	
	public static String PUBLIC_READ = "public-read";
	public static String PRIVATE = "private";
	
	public void testListRootBuckets() throws IOException  {
		AwsManager awsManager = AwsManager.getInstance();
		
		
		List<Bucket> buckets = null;

   		buckets = awsManager.getAllRootBuckets();
   		
   		for(Bucket bucket : buckets) {
   			System.out.println("Name : " + bucket.getName() + ", creation date : " + bucket.getCreationDate());   		
   		}
	}
	
	
	public void testCreateBucket() throws IOException {
		AwsManager awsManager = AwsManager.getInstance();
		//String bucketName = "imagee.sharemyvision.com";
		String bucketName = "Minh1/aaas$folder$";
		
		boolean result = awsManager.createBucket(bucketName);
        if (result) {
        	System.out.println("Created bucket '" + bucketName + "'");
        } else {
        	System.out.println("Error: can't create a bucket - please check for permission or name conflicted!!!");
        }
	}
	
	
	public void testDeleteBucket() throws IOException {
		AwsManager awsManager = AwsManager.getInstance();
		String bucketName = "Minh1";
		boolean result = awsManager.deleteBucket(bucketName);
		System.out.println("Result of deleting : " + result);
	}

	
	public void testSetAcl() throws IOException {
		String bucketName = "img2.sharemyvision.com";
    	String acl = PRIVATE;
    	AwsManager awsManager = AwsManager.getInstance();
    	
    	boolean result = awsManager.setBucketAcl(bucketName, acl);
    	System.out.println("Result of setting acl : " + result);
    }
	
	

	
	
	public void testUploadFile() throws IOException {
		AwsManager awsManager = AwsManager.getInstance();
		
		InputStream is = new BufferedInputStream (new FileInputStream ("C:\\Users\\Public\\Pictures\\Sample Pictures\\TwoJackLake.jpg"));
		awsManager.uploadFile("img2.sharemyvision.com", "a1/a2/a3/IMG1.jpg", is);
		System.out.println("Done");
	}
	
	
	public void testDeleteFile() throws IOException {
		AwsManager awsManager = AwsManager.getInstance();
    	boolean result = awsManager.deleteFile("img2.sharemyvision.com", "IMG_0257.JPG");
    	System.out.println("result : " + result);
    }
    
    public void testFileSetAcl() throws IOException {
    	AwsManager awsManager = AwsManager.getInstance();
    	boolean result = awsManager.setFileAcl("img2.sharemyvision.com", "IMG_0257.JPG", PRIVATE);

    	System.out.println("result : " + result);
    }
	
    
	public void testListBucketFiles() throws IOException {
		String bucketName = "vnspot1";
		AwsManager awsManager = AwsManager.getInstance();
		List<ListEntry> entriesList = null;

    	entriesList = awsManager.getFiles(bucketName, null, null, null, null);
    	
    	for(ListEntry entry : entriesList) {
    		System.out.println("Key : " + entry.key + ", eTag : " + entry.eTag + ", size : " + entry.size + 
    				           ", StorageClass : " + entry.storageClass + ", LastModified : " + entry.lastModified + ", ownerId : " + 
    				           entry.owner.id + ", ownerDisplayName : " + entry.owner.displayName);
    	}
	}
	
    
    ////////////////////////////////////////////////////////////////////////////////////
    ////////////QueryStringAuthGenerator
    ////////////////////////////////////////////////////////////////////////////////////
    
    public void testQueryListAllBuckets() {
    	
    }
	
	public static void main(String[] args) throws IOException  {
		TestS3Client client = new TestS3Client();
		
		//client.testListRootBuckets();
		client.testListBucketFiles();
		//client.testCreateBucket();
		//client.testSetAcl();
		//client.testDeleteBucket();
		//client.testUploadFile();
		//client.testFileSetAcl();
		//client.testDeleteFile();
		

		
	}
}
