package com.smv.util.vendor.s3;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jets3t.service.S3Service;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.security.AWSCredentials;
import org.jets3t.service.utils.Mimetypes;
import org.jets3t.service.utils.ServiceUtils;


/**
 * @author Minh Do
 * 03/01/2010
 */
public class SmvS3SignedUrl {
	private final static AWSCredentials awsCredentials = new AWSCredentials(ConfigBean.S3_ACCESS_KEY_ID,
                                                                            ConfigBean.S3_SECRET_ACCESS_KEY);
	public static int DEFAULT_TTL_IN_MINUTE = 600;
	
	public static String createSignedPutUrl(String bucketS, String filename, byte[] bytes) throws S3ServiceException {
	   
	    S3Bucket bucket = new S3Bucket(bucketS);
	    S3Object object = new S3Object(bucket, filename);
	    object.setDataInputStream(new ByteArrayInputStream(bytes));
	    
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.MINUTE, DEFAULT_TTL_IN_MINUTE);
	    Date expiryDate = cal.getTime();	    	    	   
	    
	    
		String putUrl = S3Service.createSignedPutUrl(bucket.getName(), object.getKey(), 
		                                             object.getMetadataMap(), awsCredentials, expiryDate, false);
		
		return putUrl;
	}
	
	
	public static String createSignedPutUrl(String bucketS, File file) throws NoSuchAlgorithmException, FileNotFoundException, IOException, S3ServiceException {
		S3Bucket bucket = new S3Bucket(bucketS);
	    S3Object object = new S3Object(bucket, file);
	    
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.MINUTE, DEFAULT_TTL_IN_MINUTE);
	    Date expiryDate = cal.getTime();

	    String putUrl = S3Service.createSignedPutUrl(bucket.getName(), object.getKey(), 
	    											 object.getMetadataMap(), awsCredentials, expiryDate, false);
	    
	    return putUrl;
	}
	
	public static String createSignedPutUrl(String bucketS, FileInfo fileInfo) throws S3ServiceException {
		Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.MINUTE, DEFAULT_TTL_IN_MINUTE);
	    Date expiryDate = cal.getTime();
	    
	    MetaData metaData = new MetaData();
	    metaData.addMetadata("Content-Length", String.valueOf(fileInfo.fileLength));  
	    //metaData.addMetadata("Content-Type", String.valueOf(fileInfo.mimeType));
	    //metaData.addMetadata("md5-hash", fileInfo.md5InHex);
	    //metaData.addMetadata("Content-MD5", fileInfo.md5InBase64);
	    
	    return S3Service.createSignedPutUrl(bucketS, fileInfo.filename, 
                                            metaData.getMetadataMap(), awsCredentials, 
                                            expiryDate, false);
	    
	}
	
	
	public static String createSignedGetUrl(String bucketS, String filename) throws S3ServiceException {
		return createSignedGetUrl(bucketS, filename, DEFAULT_TTL_IN_MINUTE);
	}
	
	public static String createSignedGetUrl(String bucketS, String filename, int timeToLiveInMin) throws S3ServiceException {
		S3Bucket bucket = new S3Bucket(bucketS);
	    S3Object object = new S3Object(bucket, filename);
		
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.MINUTE, timeToLiveInMin);
	    Date expiryDate = cal.getTime();
	    
	    
		String getUrl = S3Service.createSignedGetUrl(bucket.getName(), object.getKey(),  
			                                         awsCredentials, expiryDate, false);
		
		return getUrl;
	}
	
	public static String createSignedHeadUrl(String bucketS, String filename) throws S3ServiceException {
		S3Bucket bucket = new S3Bucket(bucketS);
	    S3Object object = new S3Object(bucket, filename);
		
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.MINUTE, DEFAULT_TTL_IN_MINUTE);
	    Date expiryDate = cal.getTime();

	    
	    String headUrl = S3Service.createSignedHeadUrl(bucket.getName(), object.getKey(),
	            awsCredentials, expiryDate, false);
	    
	    return headUrl;
	}
	
	
    public static String createdSignedDeleteUrl(String bucketS, String filename) throws Exception {
    	 
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, DEFAULT_TTL_IN_MINUTE);
        Date expiryDate = cal.getTime();

        String deleteUrl = S3Service.createSignedDeleteUrl(bucketS, filename,
                                                           awsCredentials, expiryDate, false);
        
        return deleteUrl;
    }
	
	
	private static class MetaData implements Serializable {

		private Map metadata = new HashMap();

		public Map getMetadataMap() {
	        return Collections.unmodifiableMap(metadata);
		}
	    

	    public Object getMetadata(String name) {
	        return this.metadata.get(name);
	    }
	    
	    public boolean containsMetadata(String name) {
	        return this.metadata.keySet().contains(name);
	    }
		
		public void addMetadata(String name, Object value) {
			this.metadata.put(name, value);
		}
		
		public void addAllMetadata(Map metadata) {
			this.metadata.putAll(metadata);
		}
	    
	    public void removeMetadata(String name) {
	        this.metadata.remove(name);
	    }
		

		public void replaceAllMetadata(Map metadata) {
			this.metadata.clear();
			addAllMetadata(metadata);
		}
		
	}
	
	public static class FileInfo {
		public String filename;
		public long fileLength;
		public String mimeType;
		public String md5InHex;
		public String md5InBase64;
	}

	
	
	
	public static FileInfo getFileInfo(File file) {
		FileInfo info = new FileInfo();
		
		info.filename = file.getName();
		info.fileLength = file.length();
		info.mimeType = Mimetypes.getInstance().getMimetype(file);
		
		byte[] bb = null;
		try {
			bb = ServiceUtils.computeMD5Hash(new FileInputStream(file));
		} catch (Exception e) {			
			e.printStackTrace();
		} 
		
		info.md5InHex = ServiceUtils.toHex(bb);
		info.md5InBase64 = ServiceUtils.toBase64(bb);
		
		return info;
	}
	
	
	public static void main(String[] args) throws S3ServiceException, NoSuchAlgorithmException, IOException {
		String bucket = "img1.sharemyvision.com";
		String file = "Winter.JPG";
		//String filepath = "C:\\dev\\md5sums\\md5sums.txt";
	    String filepath = "C:\\Dev\\resources\\Blossom.jpg";
		

	    System.out.println("Signed PUT URL: " + createSignedPutUrl(bucket, new File(filepath)));
		System.out.println("Signed PUT URL: " + createSignedPutUrl(bucket, file, new byte[] {1,2,3,4}));
	    System.out.println("Signed GET URL: " + createSignedGetUrl(bucket, file));
	    System.out.println("Signed HEAD URL: " + createSignedHeadUrl(bucket, file));

	    
	    
	    //InputStream is = new BufferedInputStream (new FileInputStream ("C:\\Documents and Settings\\Minh Do\\My Documents\\My Pictures\\misc\\IMG_0257.JPG"));
	    InputStream is = new BufferedInputStream (new FileInputStream (filepath));
	    
	    //String signedPutUrl = createSignedPutUrl(bucket, new File(filepath));
	    FileInfo info = getFileInfo(new File(filepath));
	    String signedPutUrl = createSignedPutUrl(bucket, info);
	 
	    
	    
	}
}


