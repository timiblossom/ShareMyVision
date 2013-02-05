package com.smv.test.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Random;

import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;

import com.smv.common.bean.EventDTO;
import com.smv.common.bean.ItemDTO;
import com.smv.service.file.helper.impl.FileHelperS3UrlSigner;
import com.smv.util.number.Conversion;

public class S2UrlSignerTest {
	//1: from FileService - 2:from UserService - 3: from Android
	private static String PATH = "img1.sharemyvision.com/" + userFolder(1) + "/" + getFolder();
	private static String FILE_NAME = "colosseum.jpg";
	private static String RESOURCE_PATH = "colosseum.jpg"; //"com/smv/test/file/colosseum.jpg";
	
	
	public void uploadTest() throws Exception {
		File file = getFileFromResource(RESOURCE_PATH);
		
		//Test creating upload url
		ItemDTO item = new ItemDTO();
		item.setFileName(file.getName());
		item.setFileSize(file.length());
		item.setPath(PATH);
		
		EventDTO event = new EventDTO();
		event.setItems(new ItemDTO[] {item} );
		
		EventDTO result = FileHelperS3UrlSigner.getInstance().signUploadFileUrls(event);
		System.out.println("Upload Url : " + item.getUrl());
		
		ItemDTO signedItem = result.getItems()[0];
		String putUrl = signedItem.getUrl();
		
		S3Bucket bucket = new S3Bucket(PATH);
        S3Object object = new S3Object(bucket, signedItem.getFileName());
        object.setContentLength(signedItem.getFileSize());
        object.setDataInputStream(loadImageFile2Stream(RESOURCE_PATH));

        //object.setAcl(AccessControlList.REST_CANNED_PUBLIC_READ);
        
        S3Object putObject = new RestS3Service(null).putObjectWithSignedUrl(putUrl, object);
        
        System.out.println("  Object has been uploaded to S3: " + putObject.getKey());
	}
	
	public void downloadTest() throws Exception {
		File file = getFileFromResource(RESOURCE_PATH);
		
		//Test creating download url
		ItemDTO item = new ItemDTO();
		item.setFileName(file.getName());
		item.setPath(PATH);
		
		EventDTO event = new EventDTO();
		event.setItems(new ItemDTO[] {item} );
		
		EventDTO result = FileHelperS3UrlSigner.getInstance().signDownloadFileUrl(event);
		System.out.println("Download Url : " + item.getUrl());
	}
	
	public void generateDeleteUrl() throws Exception {
		File file = getFileFromResource(RESOURCE_PATH);
		
		//Test creating delete url
		ItemDTO item = new ItemDTO();
		item.setFileName(file.getName());
		item.setPath(PATH);
		
		EventDTO event = new EventDTO();
		event.setItems(new ItemDTO[] {item} );
		
		EventDTO result = FileHelperS3UrlSigner.getInstance().signDeleteFileUrl(event);
		System.out.println("Delete Url : " + item.getUrl());
	}
	
	public void generateFileDetailUrl() throws Exception {
		File file = getFileFromResource(RESOURCE_PATH);
		
		//Test creating get file detail
		ItemDTO item = new ItemDTO();
		item.setFileName(file.getName());
		item.setPath(PATH);
		
		EventDTO event = new EventDTO();
		event.setItems(new ItemDTO[] {item} );
		
		EventDTO result = FileHelperS3UrlSigner.getInstance().signGetFileDetailUrl(event);
		System.out.println("File Detail's Url : " + item.getUrl());
	}
	
	
	//private InputStream loadImageFile2Stream(String fileInClasspath) {
	//	return this.getClass().getResourceAsStream(fileInClasspath);
	//}
	
	public URL resource2URL(String fileInClasspath) {
		return this.getClass().getResource(fileInClasspath);
	}
	
	public File getFileFromResource(String fileInClasspath) {
		return new File(resource2URL(fileInClasspath).getFile());
	}
	
	public InputStream loadImageFile2Stream(String fileInClasspath) throws FileNotFoundException {
		return new FileInputStream(getFileFromResource(fileInClasspath));
	}
	
	public static String getFolder() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DATE);
	}
	



	
	public static String userFolder(long id) {
		
		String retVal = Conversion.toBase64String(Conversion.longToByteArray(id + System.currentTimeMillis()));
		Random ran = new Random(System.currentTimeMillis());
		retVal = retVal.replace('+', '1');
		retVal = retVal.replace('/', '5');
		retVal = retVal.replace('=', 'e');
		retVal = retVal.replace('A', Conversion.Base64Chars[ran.nextInt(62)]);
		retVal = retVal.replace('B', Conversion.Base64Chars[ran.nextInt(62)]);
		retVal = retVal.replace('K', Conversion.Base64Chars[ran.nextInt(62)]);
		retVal = retVal.replace('0', Conversion.Base64Chars[ran.nextInt(62)]);
		retVal = retVal.replace('1', Conversion.Base64Chars[ran.nextInt(62)]);
		
		return retVal.substring(2);
	}
	
	public static void main(String[] args) throws Exception  {
		S2UrlSignerTest tester = new S2UrlSignerTest();
		System.out.println("Run testing...");
		
		
		//tester.uploadTest();
		//tester.downloadTest();		
		//tester.generateDeleteUrl();
		//tester.generateFileDetailUrl();
		
		for(long i=1; i<10000; i++) {
			System.out.println(userFolder(i));
			Thread.sleep(100);
		}
		
		//	System.out.println("i : " + i + "----" + Conversion.toBase64String(Conversion.longToByteArray(i + 199999)));
		
		//tester.loadImageFile2Stream(RESOURCE_PATH);
		//File file = tester.getFileFromResource(RESOURCE_PATH);
		//System.out.println("File info : " + file.getAbsolutePath() + " -- name : " + file.getName() + " -- size : " + file.length());
		
		
	}
}
