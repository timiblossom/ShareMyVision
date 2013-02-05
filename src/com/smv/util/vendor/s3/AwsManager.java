package com.smv.util.vendor.s3;



import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

import com.amazon.s3.AWSAuthConnection;
import com.amazon.s3.Bucket;
import com.amazon.s3.ListAllMyBucketsResponse;
import com.amazon.s3.ListBucketResponse;
import com.amazon.s3.ListEntry;
import com.amazon.s3.Response;
import com.amazon.s3.S3Object;
import com.silvasoftinc.s3.S3Helper;
import com.silvasoftinc.s3.S3StreamObject;


/**
 * @author Minh Do
 * 03/01/2010
 */
public class AwsManager {
	
	protected static final int RESPONSE_OK = 200;
	
	private AWSAuthConnection awsAuthConnection;
	private static AwsManager instance = new AwsManager();
	
	//protected final Log logger = LogFactory.getLog(getClass());
	
	private AwsManager() {
		init();
	}
	
	public static AwsManager getInstance() {
		return instance;
	}
	
	
	public AWSAuthConnection getAwsAuthConnection() {
		return awsAuthConnection;
	}

	public void setAwsAuthConnection(AWSAuthConnection awsAuthConnection) {
		this.awsAuthConnection = awsAuthConnection;
	}

	public void init() {
		awsAuthConnection = new AWSAuthConnection(ConfigBean.S3_ACCESS_KEY_ID, ConfigBean.S3_SECRET_ACCESS_KEY, 
				                                  false, ConfigBean.S3_HOST);
	}

	public List<Bucket> getAllRootBuckets() throws IOException {
		ListAllMyBucketsResponse buckets = null;
		buckets = awsAuthConnection.listAllMyBuckets(null);
		return buckets == null? null: buckets.entries;
	}
	
	public List<ListEntry> getFiles(String bucket, String prefix,
			                      String marker, Integer maxKeys, 
			                      Map headers) throws IOException {
	    
		ListBucketResponse entriesList = null;
		entriesList = awsAuthConnection.listBucket(bucket, prefix, marker, maxKeys, headers);
		return entriesList == null? null: entriesList.entries;
	}

	public boolean createBucket(String bucketName) throws IOException {
		Response s3Response = awsAuthConnection.createBucket(bucketName, null);
		
		if (RESPONSE_OK == s3Response.connection.getResponseCode()) {
			//logger.info("Created bucket '" + bucketName + "'");
			return true;
		} 
			
		//logger.error(s3Response.connection.getResponseMessage());
		return false;
	}
	
	public boolean deleteBucket(String bucketName) throws IOException {
		Response s3Response = awsAuthConnection.deleteBucket(bucketName, null);
		
		//TODO: fix this block since it seems like that REPOONSE != 200 when there is a successfull delete
		if (RESPONSE_OK == s3Response.connection.getResponseCode()) {
			//logger.info("Deleted bucket '" + bucketName + "'");
			return true;
		} 
			
		//logger.error(s3Response.connection.getResponseMessage());
		return false;
	}
	
	public String getBucketAcl(String bucket) throws IOException {
		S3Object s3Object = null;
		s3Object = awsAuthConnection.getACL(bucket, null, null).object;
		if (s3Object == null) {
			//logger.error("Error: bucket '" + bucket + "' not found");
            throw new IOException("Error: bucket '" + bucket + "' not found");
		}
		String currentACL = new String(s3Object.data);
		//logger.info(currentACL);

		return currentACL;
	}

	public boolean setBucketAcl(String bucket, String acl) throws IOException {

    	String currentAcl = getBucketAcl(bucket);
    	//logger.info("Current ACL == " + currentAcl);
    	
		String currenId = currentAcl.substring(currentAcl.indexOf("<ID>") + 4, currentAcl.indexOf("</ID>"));

		String aclXmlDoc = "";
		if (acl.equals("private")) {
			aclXmlDoc = S3Helper.getACLTemplatePrivate(currenId);
		} else if (acl.equals("public-read")) {
			aclXmlDoc = S3Helper.getACLTemplatePublicRead(currenId);
		} else if (acl.equals("public-read-write")) {
			aclXmlDoc = S3Helper.getACLTemplatePublicReadWrite(currenId);
		} else {
			//logger.info(acl + " not supported at this time.");
			return false;
		}
		//logger.info("aclXmlDoc: " + aclXmlDoc);

		Response s3Response = awsAuthConnection.putBucketACL(bucket, aclXmlDoc, null);
		if (RESPONSE_OK == s3Response.connection.getResponseCode()) {
			//logger.info("Set ACL for bucket '" + bucket + "' to " + acl);
			return true;
		} 
			
		//logger.error("Error: could not set ACL for bucket '" + bucket 
		//			+ "' to " + acl);
			
		//logger.error(s3Response.connection.getResponseMessage());
		
		return false;
    }
	
	public boolean deleteFile(String bucket, String item) throws MalformedURLException, IOException {
		Response s3Response = awsAuthConnection.delete(bucket, item, null);
		if (RESPONSE_OK == s3Response.connection.getResponseCode()) {
			//logger.info("Deleted item '" + bucket + "/" + item + "'");
			return true;
		} 
		
		//logger.info(s3Response.connection.getResponseMessage());
		return false;
	}
	
	public String getFileAcl(String bucket, String item) throws MalformedURLException, IOException {
		S3Object s3Object = null;
		s3Object = awsAuthConnection.getACL(bucket, item, null).object;
		if (s3Object == null) {
			//logger.info("Error: item '" + bucket + "/" + item + "' not found");
		}
		String currentACL = new String(s3Object.data);
		//logger.info(currentACL);
		return currentACL;
	}
	
	public boolean setFileAcl(String bucket, String item, String acl) throws MalformedURLException, IOException {
		String currentAcl = getFileAcl(bucket, item);
		String currentId = currentAcl.substring(currentAcl.indexOf("<ID>") + 4,
				currentAcl.indexOf("</ID>"));

		String aclXmlDoc = "";
		if (acl.equals("private")) {
			aclXmlDoc = S3Helper.getACLTemplatePrivate(currentId);
		} else if (acl.equals("public-read")) {
			aclXmlDoc = S3Helper.getACLTemplatePublicRead(currentId);
		} else if (acl.equals("public-read-write")) {
			aclXmlDoc = S3Helper.getACLTemplatePublicReadWrite(currentId);
		} else {
			//logger.info(acl + " not supported at this time.");
			return false;
		}
		//logger.info("aclXmlDoc: " + aclXmlDoc);

		Response s3Response = awsAuthConnection.putACL(bucket, item, aclXmlDoc, null);
		if (RESPONSE_OK == s3Response.connection.getResponseCode()) {
			//logger.info("Set ACL for item '" + bucket + "/" + item + "' to " + acl);
			return true;
		} else {
			//logger.info("Error: could not set ACL for item '" + bucket + "/"
			//		+ item + "' to " + acl);
			//logger.info(s3Response.connection.getResponseMessage());
		}

		return false;
	}
	
	public boolean uploadFile(String bucket, String fileName, InputStream stream) throws MalformedURLException, IOException {
		S3StreamObject s3Object = new S3StreamObject(stream, null);
		Response s3Response = awsAuthConnection.putStream(bucket, fileName, s3Object, null);
		
		if (RESPONSE_OK == s3Response.connection.getResponseCode()) {
			//logger.info("Uploaded item '" + bucket + "/" + fileName + "'");
			return true;
		} 
		
		//logger.info(s3Response.connection.getResponseMessage());
		return false;	
	}
	
}