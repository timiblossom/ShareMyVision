package com.smv.test.s3;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Calendar;
import java.util.Date;

import org.jets3t.service.S3Service;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.security.AWSCredentials;
import org.jets3t.service.utils.signedurl.SignedUrlHandler;

import com.smv.util.io.FileUtil;

/**
 * Demonstrates how to create and use Signed URLs.
 */
public class TestJetS3t {
    
    /*
     * Change the name of this bucket to a bucket of your own.
     */
    ///private static final String myBucketName = "img1.sharemyvision.com";
	private static final String myBucketName = "vnspot";

    public static void main1(String[] args) throws Exception {
    	InputStream is = new BufferedInputStream (new FileInputStream ("C:\\Users\\Public\\Pictures\\Sample Pictures\\TwoJackLake.jpg"));
    	File file = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\TwoJackLake.jpg");
        // Initialise a SignedUrlHandler, which is an interface implemented by classes able to 
        // perform operations in S3 using signed URLs (no AWS Credentials required).
        // The RestS3Service provides an implementation of this interface in JetS3t. 
        SignedUrlHandler signedUrlHandler = new RestS3Service(null);

        // Create a bucket to test reading and writing to        
        S3Bucket bucket = new S3Bucket(myBucketName);
        System.out.println("bucket.getName() : " + bucket.getName());
        // Create an object to use for testing.
       // S3Object object = new S3Object(bucket, "Minhsss", "Hello World!");
        
        S3Object object = new S3Object(bucket, "TwoJackLake.jpg");
        object.setDataInputStream(is);
        object.setContentLength(file.length());
        
        System.out.println("file length : " + file.length());
        
        //S3Object object1 = new S3Object(bucket, "Minhsss");
        
        // Determine what the time will be in 5 minutes - our signed URLs will be valid for 5 minutes only.
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 5);
        Date expiryDate = cal.getTime();

        /*
         * Generate the signed URL strings for PUT, GET, HEAD and DELETE operations, using the
         * AWS Credentials in the samples.properties file. 
         */
        AWSCredentials awsCredentials = SamplesUtils.loadAWSCredentials();
        
        // Create a signed HTTP PUT URL valid for 5 minutes.        
        String putUrl = S3Service.createSignedPutUrl(bucket.getName(), object.getKey(), 
                                                     object.getMetadataMap(), awsCredentials, 
                                                     expiryDate, false);
       // String putUrl = S3Service.createSignedPutUrl(bucket.getName(), nFolder.getKey(), 
       // 		nFolder.getMetadataMap(), awsCredentials, expiryDate, false);
        
        
        // Create a signed HTTP GET URL valid for 5 minutes.
        String getUrl = S3Service.createSignedGetUrl(bucket.getName(), object.getKey(),  
            awsCredentials, expiryDate, false);

        // Create a signed HTTP HEAD URL valid for 5 minutes.
        String headUrl = S3Service.createSignedHeadUrl(bucket.getName(), object.getKey(),
            awsCredentials, expiryDate, false);

        // Create a signed HTTP DELETE URL valid for 5 minutes.
        String deleteUrl = S3Service.createSignedDeleteUrl(bucket.getName(), object.getKey(),
            awsCredentials, expiryDate, false);
        
        System.out.println("Signed PUT URL: " + putUrl);
        System.out.println("Signed GET URL: " + getUrl);
        System.out.println("Signed HEAD URL: " + headUrl);
        System.out.println("Signed DELETE URL: " + deleteUrl);

    
        System.out.println("Performing PUT with signed URL");
        S3Object putObject = signedUrlHandler.putObjectWithSignedUrl(putUrl, object);
        //S3Object putObject = signedUrlHandler.putObjectWithSignedUrl(putUrl, nFolder);
        System.out.println("  Object has been uploaded to S3: " + putObject.getKey());

  

        System.out.println("Performing HEAD with signed URL");        
        S3Object headObject = signedUrlHandler.getObjectDetailsWithSignedUrl(headUrl);
        System.out.println("  Size of object in S3: " + headObject.getContentLength());

                
        System.out.println("Performing GET with signed URL");
        S3Object getObject = signedUrlHandler.getObjectWithSignedUrl(getUrl);
        //String contentData = (new BufferedReader(new InputStreamReader(getObject.getDataInputStream()))).readLine();
        //System.out.println("  Content of object in S3: " + contentData);
        FileUtil.saveImage(getObject.getDataInputStream(), new File("c:\\dev\\temp\\mypic.jpg"));
        
        /*
        System.out.println("Performing DELETE with signed URL");
        signedUrlHandler.deleteObjectWithSignedUrl(deleteUrl);
        System.out.println("  Object deleted - the example is finished");
        */
        System.out.println("Done...");
        
    }
    

    public static void main(String[] args) throws Exception {
    	String myBucketName = "TestVnspot1";
    	
    	//createFirstLevelBucket(myBucketName);
    	//create2ndLevelFolder(myBucketName);
    	//create3rdLevelFolder(myBucketName);
    	uploadImage(myBucketName + "/dddd/saigon1");
    	
    	//downloadImage(myBucketName + "/dddd1", "Blossom.jpg");
    	//getFileDetail(myBucketName + "/dddd/minh", "Blossom2.jpg");
    	//getFileDetail(myBucketName + "/dddd", "minh");
    	//deleteFile(myBucketName + "/dddd1", "TwoJackLake.jpg");
    }
    
    private static void createFirstLevelBucket(String bucketName) throws Exception {

        AWSCredentials awsCredentials = SamplesUtils.loadAWSCredentials();
        S3Service s3Service = new RestS3Service(awsCredentials);

        S3Bucket bucket = s3Service.createBucket(bucketName);                               
    }
    
    private static void create2ndLevelFolder(String myBucketName) throws Exception {
    	SignedUrlHandler signedUrlHandler = new RestS3Service(null);
     
        S3Bucket bucket = new S3Bucket(myBucketName);
        
        S3Object nFolder = new S3Object(bucket, "ddda1_$folder$");       
        nFolder.setContentLength(0);
        
        
        // Determine what the time will be in 5 minutes - our signed URLs will be valid for 5 minutes only.
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 5);
        Date expiryDate = cal.getTime();

        AWSCredentials awsCredentials = SamplesUtils.loadAWSCredentials();
        
        // Create a signed HTTP PUT URL valid for 5 minutes.        
        String putUrl = S3Service.createSignedPutUrl(bucket.getName(), nFolder.getKey(), 
        	                                        	nFolder.getMetadataMap(), 
        	                                        	awsCredentials, expiryDate, false);
        
        
        System.out.println("Signed PUT URL: " + putUrl);

    
        System.out.println("Performing PUT with signed URL");
        S3Object putObject = signedUrlHandler.putObjectWithSignedUrl(putUrl, nFolder);
        System.out.println(putObject.getKey() + " folder has been created");
    }
    
    
    private static void create3rdLevelFolder(String myBucketName) throws Exception {
    	SignedUrlHandler signedUrlHandler = new RestS3Service(null);
     
        S3Bucket bucket = new S3Bucket(myBucketName);
        
        S3Object nFolder = new S3Object(bucket, "ddda1/minh_$folder$");
        nFolder.setContentLength(0);
        
        
        // Determine what the time will be in 5 minutes - our signed URLs will be valid for 5 minutes only.
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 5);
        Date expiryDate = cal.getTime();

        AWSCredentials awsCredentials = SamplesUtils.loadAWSCredentials();
        
        // Create a signed HTTP PUT URL valid for 5 minutes.        
        String putUrl = S3Service.createSignedPutUrl(bucket.getName(), nFolder.getKey(), 
        	                                        	nFolder.getMetadataMap(), 
        	                                        	awsCredentials, expiryDate, false);
        
        
        System.out.println("Signed PUT URL: " + putUrl);

    
        System.out.println("Performing PUT with signed URL");
        S3Object putObject = signedUrlHandler.putObjectWithSignedUrl(putUrl, nFolder);
        System.out.println(putObject.getKey() + " folder has been created");
    }
    
    
    private static void uploadImage(String myBucketName) throws Exception {
    	
    	File file = new File("C:\\Dev\\resources\\Blossom.jpg");
    	InputStream is = new BufferedInputStream (new FileInputStream (file));
    	

        // Create a bucket to test reading and writing to        
        S3Bucket bucket = new S3Bucket(myBucketName);
        S3Object object = new S3Object(bucket, "Blossom4.jpg");
        object.setContentLength(file.length());
        
        //System.out.println("file length : " + file.length());
        
   
        
        // Determine what the time will be in 5 minutes - our signed URLs will be valid for 5 minutes only.
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 5);
        Date expiryDate = cal.getTime();

        /*
         * Generate the signed URL strings for PUT, GET, HEAD and DELETE operations, using the
         * AWS Credentials in the samples.properties file. 
         */
        AWSCredentials awsCredentials = SamplesUtils.loadAWSCredentials();
        
        // Create a signed HTTP PUT URL valid for 5 minutes.        
        String putUrl = S3Service.createSignedPutUrl(bucket.getName(), object.getKey(), 
                                                     object.getMetadataMap(), awsCredentials, expiryDate, false);
        
        
       

     
        System.out.println("Signed PUT URL: " + putUrl);
       
    
        System.out.println("Performing PUT with signed URL");
        object.setDataInputStream(is);
        S3Object putObject = new RestS3Service(null).putObjectWithSignedUrl(putUrl, object);
        
        System.out.println("  Object has been uploaded to S3: " + putObject.getKey());

 
    }
    
   
    private static void downloadImage(String myBucketName, String objectKey) throws Exception {
    	
    	//File file = new File("C:\\Dev\\resources\\Blossom_dl.jpg");       
        SignedUrlHandler signedUrlHandler = new RestS3Service(null);       
        S3Bucket bucket = new S3Bucket(myBucketName);


        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 5);
        Date expiryDate = cal.getTime();


        AWSCredentials awsCredentials = SamplesUtils.loadAWSCredentials();     

        String getUrl = S3Service.createSignedGetUrl(bucket.getName(), objectKey,  
                                                     awsCredentials, expiryDate, false);

       
        System.out.println("Signed GET URL: " + getUrl);
       
        System.out.println("Performing GET with signed URL");
        S3Object getObject = signedUrlHandler.getObjectWithSignedUrl(getUrl);
        
       
        FileUtil.saveImage(getObject.getDataInputStream(), new File("c:\\dev\\temp\\mypic.jpg"));
        
    } 

    
    private static void getFileDetail(String myBucketName, String objectKey) throws Exception {

        SignedUrlHandler signedUrlHandler = new RestS3Service(null);
 
        // Determine what the time will be in 5 minutes - our signed URLs will be valid for 5 minutes only.
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 5);
        Date expiryDate = cal.getTime();

        AWSCredentials awsCredentials = SamplesUtils.loadAWSCredentials();

        // Create a signed HTTP HEAD URL valid for 5 minutes.
        String headUrl = S3Service.createSignedHeadUrl(myBucketName, objectKey,
                                                       awsCredentials, expiryDate, false);

      
        System.out.println("Signed HEAD URL: " + headUrl);
        System.out.println("Performing HEAD with signed URL");        
        S3Object headObject = signedUrlHandler.getObjectDetailsWithSignedUrl(headUrl);
        System.out.println("  Size of object in S3: " + headObject.getContentLength());
        System.out.println("  Bucket Name: " + headObject.getBucketName());
        System.out.println("  Content Disposition : " + headObject.getContentDisposition());
        System.out.println("  Content Language : " + headObject.getContentLanguage());
        System.out.println("  Content Type : " + headObject.getContentType());
        System.out.println("  ETag : " + headObject.getETag());
        System.out.println("  Key : " + headObject.getKey());
        System.out.println("  Md5HashAsBase64 : " + headObject.getMd5HashAsBase64());
        System.out.println("  StorageClass : " + headObject.getStorageClass());
        System.out.println("  Acl : " + headObject.getAcl());
        System.out.println("  LastModifiedDate : " + headObject.getLastModifiedDate());

    }
    
    
    private static void deleteFile(String myBucketName, String objectKey) throws Exception {
 
        SignedUrlHandler signedUrlHandler = new RestS3Service(null);

        // Determine what the time will be in 5 minutes - our signed URLs will be valid for 5 minutes only.
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 5);
        Date expiryDate = cal.getTime();


        AWSCredentials awsCredentials = SamplesUtils.loadAWSCredentials();

        // Create a signed HTTP DELETE URL valid for 5 minutes.
        String deleteUrl = S3Service.createSignedDeleteUrl(myBucketName, objectKey,
                                                           awsCredentials, expiryDate, false);
        

        System.out.println("Signed DELETE URL: " + deleteUrl);

        System.out.println("Performing DELETE with signed URL");
        signedUrlHandler.deleteObjectWithSignedUrl(deleteUrl);
        System.out.println("  Object deleted - the example is finished");

    }
  
}
