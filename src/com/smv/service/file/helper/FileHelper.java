package com.smv.service.file.helper;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.jets3t.service.S3ServiceException;

import com.smv.common.Constant;
import com.smv.common.bean.EventDTO;
import com.smv.service.file.config.ResourceManager;
import com.smv.service.file.db.dao.FileSystemDAO;
import com.smv.service.file.db.dbobject.FileSystemDO;
import com.smv.util.db.AbstractDO;
import com.smv.util.encryption.TriDesCipher;
import com.smv.util.number.Conversion;
import com.smv.util.thread.LocalThreadObjectsHolder;
import com.smv.util.vendor.s3.SmvS3SignedUrl;


/**
 * @author Minh Do
 * 03/01/2010
 */
public class FileHelper {
	private static final Logger LOGGER = Logger.getLogger(FileHelper.class);
	private static final Random RANDOM = new Random(System.currentTimeMillis());
	private static final long BASE_NUM = 2000000000;
	private static final int DEFAULT_TTL_IN_MIN = 512640; //1 year
	
	private static TriDesCipher getCipher() {
		TriDesCipher cipher = (TriDesCipher) LocalThreadObjectsHolder.getLocalThreadObject("TRIPLE_DES_CIPHER");
		if (cipher == null) {
			try {
				cipher = new TriDesCipher(Constant.TRIPLE_DES_KEY);
				LocalThreadObjectsHolder.setLocalThreadObject("TRIPLE_DES_CIPHER", cipher);
			} catch (NoSuchAlgorithmException e) {
				LOGGER.error(e);
			} catch (InvalidKeySpecException e) {
				LOGGER.error(e);
			} catch (IOException e) {
				LOGGER.error(e);
			} catch (InvalidKeyException e) {
				LOGGER.error(e);
			}
		}

		return (TriDesCipher) cipher;
	}

	public static String generateGuid(EventDTO dto, Map httpEnv) {		
		byte[] serverIp = null;
		byte[] clientIp = new byte[4];

		long now = System.currentTimeMillis();		

		try {
			InetAddress addr = InetAddress.getLocalHost();	    
			serverIp = addr.getAddress();
			String callerIp = (String) httpEnv.get(Constant.REMOTE_ADDR);
			clientIp = Conversion.getByteArrayFromIp(callerIp);

			int numUpload = 0; //will change this

			return generateGuid(clientIp, serverIp, 
					now, numUpload);

		} catch (UnknownHostException e) {
			e.printStackTrace();	    	
		}

		return "BadEvent";

	}


	public static String generateGuid(byte[] clientIP, byte[] serverIP, long time, int numUpload) {

		byte[] numInBytes = Conversion.intToByteArray(numUpload);
		byte[] timeInBytes = Conversion.longToByteArray(time);


		int n = clientIP.length + serverIP.length + timeInBytes.length + numInBytes.length;
		byte[] result = new byte[n]; //should be 4 + 4 + 8 + 4 = 20
		int offset = 1;

		//clientIp : [n-1]-[n-4]		
		for(int i=0; i<clientIP.length; i++ ) {
			result[n-offset] = clientIP[i];
			offset++;
		}

		//serverIp : [n-5]-[n-8]
		for(int i=0; i<serverIP.length; i++) {
			result[n-offset] = serverIP[i];
			offset++;
		}

		//time : [n-9] - [n-16]
		for(int i=0; i<timeInBytes.length; i++) {
			result[n-offset] = timeInBytes[i];
			offset++;
		}

		//numUpload : [n-17]-[n-20]
		for(int i=0; i<numInBytes.length; i++) {
			result[n-offset] = numInBytes[i];
			offset++;
		}

		String guid = getCipher().encrypt(result);


		return guid;
	}


	public static String generateGuid(String clientIp, String serverIp, int numUpload) {
		long now = System.currentTimeMillis();

		return generateGuid(Conversion.getByteArrayFromIp(clientIp), 
				Conversion.getByteArrayFromIp(serverIp), 
				now, numUpload);
	}

	
	public static String generateFolderName(long id) {

		String nowStr = Conversion.toBase64String(Conversion.longToByteArray(System.currentTimeMillis()));
		
		nowStr = nowStr.replace('+', '1');
		nowStr = nowStr.replace('/', '5');
		nowStr = nowStr.replace('=', 'e');
		//retVal = retVal.replace('A', Conversion.Base64Chars[ran.nextInt(62)]);
		//retVal = retVal.replace('B', Conversion.Base64Chars[ran.nextInt(62)]);
		//retVal = retVal.replace('K', Conversion.Base64Chars[ran.nextInt(62)]);
		//retVal = retVal.replace('0', Conversion.Base64Chars[ran.nextInt(62)]);
		//retVal = retVal.replace('1', Conversion.Base64Chars[ran.nextInt(62)]);

		String idStr = Conversion.toBase64String(Conversion.longToByteArray(id + BASE_NUM));
		idStr = idStr.replace('+', '1');
		idStr = idStr.replace('/', '5');
		idStr = idStr.replace('=', 'e');
		
		
		return idStr.substring(6) + nowStr.substring(5);
	}


	
	public static FileSystemDO getOrGenerateFileSystemDO(Long uid) {
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter getOrGenerateFileSystemDO - uid : { uid = " + uid + "}");
		}
		
		FileSystemDO retVal = FileSystemDAO.geFileSystemByUid(uid);

		//TODO: will move this saving into the post registration
		if (retVal == null) {
			retVal = new FileSystemDO();
			retVal.setOperation(AbstractDO.CREATE);
			int index = ResourceManager.RANDOM.nextInt(ResourceManager.NUM_SERVERS) + 1;
			retVal.setServer("img" + index + ".sharemyvision.com");
			retVal.setId(uid);
			retVal.setFileCount(0);
			retVal.setEventCount(0);
			retVal.setFileId1(generateFolderName(uid.longValue()));
			retVal.setUsedSpace(0);
			
			FileSystemDAO fsBo = new FileSystemDAO(retVal);
			fsBo.save(true, false);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit getOrGenerateFileSystemDO - uid : { uid = " + uid + "}");
		}
		
		return retVal;
	}
	
	
	public static String[] getPathAndFileName(String uploadUrlStr) {
		String[] retVal = new String[2];
		
		retVal[0] = uploadUrlStr.substring(25);
		
		retVal[1] = retVal[0].substring(retVal[0].lastIndexOf('/') + 1, retVal[0].lastIndexOf('?'));
		retVal[0] = retVal[0].substring(0, retVal[0].lastIndexOf('/'));
		
		return retVal;
	}
	
	
	public static String generateDownloadUrlForStoring(String origUrl) {
		String[] values = getPathAndFileName(origUrl);
		try {
			return "http://" + SmvS3SignedUrl.createSignedGetUrl(values[0], values[1], DEFAULT_TTL_IN_MIN).substring(25);
		} catch (S3ServiceException e) {			
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String generateDownloadUrlForStoring(String[] values) {
		try {
			return "http://" + SmvS3SignedUrl.createSignedGetUrl(values[0], values[1], DEFAULT_TTL_IN_MIN).substring(25);
		} catch (S3ServiceException e) {			
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args)  {
		//System.out.println(generateGuid("192.168.0.1", "10.10.31.85", 10));
		//System.out.println(generateFolderName(1L));
		
		String[] values = getPathAndFileName("https://s3.amazonaws.com/img1.sharemyvision.com/abc/d2010826/colosseum.jpg?AWSAccessKeyId=0MP9F4G25D5XT2V8V082&Expires=1285551215&Signature=qt6Wq0c48AYmSXIMrRBwYcq2cf0%3D");
		System.out.println("0 : " + values[0]);
		System.out.println("1 : " + values[1]);
		
		System.out.println(generateDownloadUrlForStoring("https://s3.amazonaws.com/img1.sharemyvision.com/abc/d2010826/colosseum.jpg?AWSAccessKeyId=0MP9F4G25D5XT2V8V082&Expires=1285551215&Signature=qt6Wq0c48AYmSXIMrRBwYcq2cf0%3D"));
	}
}
