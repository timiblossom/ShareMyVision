package com.smv.service.file.s3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jets3t.service.S3Service;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.acl.AccessControlList;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.security.AWSCredentials;
import org.jets3t.service.utils.signedurl.SignedUrlHandler;

import com.amazon.s3.ListEntry;
import com.smv.service.file.config.AwsConfig;
import com.smv.util.vendor.s3.AwsManager;


/**
 * @author Minh Do
 * 07/22/2010
 */
public class AwsUtils {
	private static final Logger LOGGER = Logger.getLogger(AwsUtils.class);
	private static AWSCredentials awsCredentials = new AWSCredentials(AwsConfig.S3_ACCESS_KEY_ID, AwsConfig.S3_SECRET_ACCESS_KEY);  
	private static int EXPIRATION_INTERVAL_IN_MIN = 5;

    public static void createRootBucket(String bucketName) throws Exception {
        S3Service s3Service = new RestS3Service(awsCredentials);        
        S3Bucket bucket = new S3Bucket(bucketName);
        bucket.setAcl(AccessControlList.REST_CANNED_PRIVATE);
        s3Service.createBucket(bucket);        
    }
    
    public static String signCreatingUserHomeUrl(String rootBucket, String entryName) throws S3ServiceException {   
        S3Bucket bucket = new S3Bucket(rootBucket);
        bucket.setAcl(AccessControlList.REST_CANNED_PRIVATE);
        
        S3Object folder = new S3Object(bucket, entryName + "_$folder$");       
        folder.setContentLength(0);        
        
        // Determine what the time will be in EXPIRATION_INTERVAL_IN_MIN minutes - our signed URLs will be valid for EXPIRATION_INTERVAL_IN_MIN minutes only.
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, EXPIRATION_INTERVAL_IN_MIN);
        Date expiryDate = cal.getTime();
        
        // Create a signed HTTP PUT URL valid for EXPIRATION_INTERVAL_IN_MIN minutes.        
        String putUrl = S3Service.createSignedPutUrl(bucket.getName(), folder.getKey(), folder.getMetadataMap(), 
        	                                         awsCredentials, expiryDate, false);
           
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("Signed PUT URL: " + putUrl);
        }
        
        return putUrl;
    }
    
    public static void createUserHome(String rootBucket, String folderName, String signedPutUrl) throws S3ServiceException {
    	SignedUrlHandler signedUrlHandler = new RestS3Service(null);     
        S3Bucket bucket = new S3Bucket(rootBucket);        
        S3Object s3Obj = new S3Object(bucket, folderName + "_$folder$");       
        s3Obj.setContentLength(0);  
        //folder.setAcl(AccessControlList.REST_CANNED_PRIVATE);
        
        S3Object putObject = signedUrlHandler.putObjectWithSignedUrl(signedPutUrl, s3Obj);
        
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("create2ndLevelBucket::" + putObject.getKey() + " folder has been created");
        }
    }
    
    public static String signCreatingUserBucketUrl(String rootBucket, String parentName, String entryName) throws S3ServiceException {    	     
        S3Bucket bucket = new S3Bucket(rootBucket);
        
        S3Object s3Obj = new S3Object(bucket, parentName + "/" + entryName + "_$folder$");
        s3Obj.setContentLength(0);
        //nFolder.setAcl(AccessControlList.REST_CANNED_PRIVATE);
        
        // Determine what the time will be in 5 minutes - our signed URLs will be valid for 5 minutes only.
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, EXPIRATION_INTERVAL_IN_MIN);
        Date expiryDate = cal.getTime();
        
        // Create a signed HTTP PUT URL valid for 5 minutes.        
        String putUrl = S3Service.createSignedPutUrl(bucket.getName(), s3Obj.getKey(), 
        	                                         s3Obj.getMetadataMap(), 
        	                                         awsCredentials, expiryDate, false);
        
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("create2ndLevelBucket::Signed PUT URL: " + putUrl);
        }
        
        return putUrl;        
    }
    
    public static void createUserBucket(String rootBucket, String folderName, String entryName, String signedPutUrl) throws S3ServiceException {
    	SignedUrlHandler signedUrlHandler = new RestS3Service(null);
    	S3Bucket bucket = new S3Bucket(rootBucket);
        
        S3Object s3Obj = new S3Object(bucket, folderName + "/" + entryName + "_$folder$");
        s3Obj.setContentLength(0);

        S3Object putObject = signedUrlHandler.putObjectWithSignedUrl(signedPutUrl, s3Obj);
        
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("create3rdLevelBucket::" + putObject.getKey() + " folder has been created");
        }
    }

    
	public static void listBucketFiles(String rootBucket, String keyword) throws IOException {
		AwsManager awsManager = AwsManager.getInstance();
		List<ListEntry> entriesList = null;

    	entriesList = awsManager.getFiles(rootBucket, keyword, null, null, null);
    	
    	for(ListEntry entry : entriesList) {
    		LOGGER.info("Key : " + entry.key + ", eTag : " + entry.eTag + ", size : " + entry.size + 
    				     ", StorageClass : " + entry.storageClass + ", LastModified : " + entry.lastModified + 
    				     ", ownerId : " + entry.owner.id + ", ownerDisplayName : " + entry.owner.displayName);
    	}
	}
	
	
	public static String signUploadUrl(String folderPath, String fileName, long fileSize) throws Exception {

        S3Bucket bucket = new S3Bucket(folderPath);                
        S3Object object = new S3Object(bucket, fileName);
        
        //object.setDataInputStream(is);
        object.setContentLength(fileSize);
 
        // Determine what the time will be in 5 minutes - our signed URLs will be valid for 5 minutes only.
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, EXPIRATION_INTERVAL_IN_MIN);
        Date expiryDate = cal.getTime();

        
        // Create a signed HTTP PUT URL valid for 5 minutes.        
        String putUrl = S3Service.createSignedPutUrl(bucket.getName(), object.getKey(), 
                                                     object.getMetadataMap(), awsCredentials, expiryDate, false);
        
        
       
        return putUrl;
	}
	
	public static void uploadImage(File localFile, String putUrl) throws S3ServiceException, FileNotFoundException {
     
		SignedUrlHandler signedUrlHandler = new RestS3Service(null);
		InputStream is = new BufferedInputStream (new FileInputStream(localFile));
        S3Object object = new S3Object();        
        
        object.setDataInputStream(is);
        object.setContentLength(localFile.length());
		
        S3Object putObject = signedUrlHandler.putObjectWithSignedUrl(putUrl, object);
        
        if (LOGGER.isDebugEnabled()) {
        	LOGGER.debug("  Object has been uploaded to S3: " + putObject.getKey());
        }
    }
    
	
	public static String signDownloadUrl(String s3FolderPath, String s3EntryName) throws S3ServiceException {
		Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, EXPIRATION_INTERVAL_IN_MIN);
        Date expiryDate = cal.getTime();
        
        return S3Service.createSignedGetUrl(s3FolderPath, s3EntryName,  
                                            awsCredentials, expiryDate, false);
        
	}
	
	public static void downloadImage(String localPath, String localFileName, String signedUrl) throws S3ServiceException {
		SignedUrlHandler signedUrlHandler = new RestS3Service(null);  
		S3Object getObject = signedUrlHandler.getObjectWithSignedUrl(signedUrl);
		saveImage(getObject.getDataInputStream(), new File(localPath + File.separatorChar + localFileName));
	}    
    
    private static void fastChannelCopy(final ReadableByteChannel src, final WritableByteChannel dest) throws IOException {
    	final ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
    	while (src.read(buffer) != -1) {
    		// prepare the buffer to be drained
    		buffer.flip();
    		// write to the channel, may block
    		dest.write(buffer);
    		// If partial transfer, shift remainder down
    		// If buffer is empty, same as doing clear()
    		buffer.compact();
    	}
    	// EOF will leave buffer in fill state
    	buffer.flip();
    	// make sure the buffer is fully drained.
    	while (buffer.hasRemaining()) {
    		dest.write(buffer);
    	}
    }

    
    private static boolean saveImage(InputStream is, File file) {    
    	FileOutputStream fos = null;
    	BufferedOutputStream bos = null;
    	DataOutputStream dos = null;
    	byte[] buf = new byte[1024];
    	
    	try {
			fos = new FileOutputStream(file);		
			bos = new BufferedOutputStream(fos);
			final ReadableByteChannel inputChannel = Channels.newChannel(is);
	    	final WritableByteChannel outputChannel = Channels.newChannel(bos);
	    	fastChannelCopy(inputChannel, outputChannel);
	    	// closing the channels
	    	inputChannel.close();
	    	outputChannel.close();
	    	
			bos.close();
			fos.close();
			is.close();			
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return true;
    }


    public static String signDeleteUrl(String s3FolderPath, String objectKey) throws S3ServiceException  {
    	Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, EXPIRATION_INTERVAL_IN_MIN);
        Date expiryDate = cal.getTime();
        
        String deleteUrl = S3Service.createSignedDeleteUrl(s3FolderPath, objectKey,
                                                           awsCredentials, expiryDate, false);
        
        return deleteUrl;
    }
    
    public static void deleteEntry(String signedUrl) throws S3ServiceException  {
    	SignedUrlHandler signedUrlHandler = new RestS3Service(null);
    	signedUrlHandler.deleteObjectWithSignedUrl(signedUrl);    	
    }
    
    public static String signFileDetailUrl(String s3FolderPath, String objectKey) throws S3ServiceException  {
    	Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, EXPIRATION_INTERVAL_IN_MIN);
        Date expiryDate = cal.getTime();
        
        String headUrl = S3Service.createSignedHeadUrl(s3FolderPath, objectKey,
                                                       awsCredentials, expiryDate, false);
        
        return headUrl;
    }
    
    public static S3Object getFileDetail(String signedUrl) throws S3ServiceException {
    	SignedUrlHandler signedUrlHandler = new RestS3Service(null);
    	S3Object headObject = signedUrlHandler.getObjectDetailsWithSignedUrl(signedUrl);
    	return headObject;
    }

    
    
    public static void main(String[] args) throws Exception  {
    	String rootName = "vnspot1";
    	String secondLevelFolder = "dddd";
    	String thirdLevelFolder = "paris";
    	
    	//create1stLevelBucket(rootName);
    	String signedUrl = signCreatingUserHomeUrl(rootName, secondLevelFolder);
    	//createUserHome(rootName, secondLevelFolder, signedUrl);
    	
    	//signedUrl = signCreatingUserBucketUrl(rootName, secondLevelFolder, thirdLevelFolder);    	
    	//createUserBucket(rootName, secondLevelFolder, thirdLevelFolder, signedUrl);
    	
    	//listBucketFiles("vnspot1", "");
    	
    	//Upload file
    	File file = new File("C:\\Dev\\resources\\Blossom.jpg");
    	String signUploadUrl = signUploadUrl("/TestVnspot1/dddd1", file.getName(), file.length());
    	System.out.println("signUploadUrl::: " + signUploadUrl);
    	
    	uploadImage(file, signUploadUrl);
    	
    	
    	//Download file
    	String signDownloadUrl = signDownloadUrl("/TestVnspot1/dddd1/Blossom.jpg", null);
    	System.out.println("signDownloadUrl:: " + signDownloadUrl);
    	downloadImage("C:\\Dev\\temp", "Blossom11.jpg", signDownloadUrl);
    	
    	String signFileDetailUrl = signFileDetailUrl("/TestVnspot1/dddd1/Blossom.jpg", null);
    	System.out.println("signFileDetailUrl:: " + signFileDetailUrl);
    	
    	S3Object obj = getFileDetail(signFileDetailUrl);
    	System.out.println(obj.getContentLength());
    	
    	String signDeleteUrl = signDeleteUrl("/TestVnspot1/dddd1/Blossom.jpg", null);
    	deleteEntry(signDeleteUrl);
    	
    	System.out.println("Done");
    }
}
