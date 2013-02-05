package com.smv.test.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;

import com.smv.common.bean.EventDTO;
import com.smv.common.bean.ItemDTO;
import com.smv.service.file.helper.impl.FileHelperS3UrlSigner;
import com.smv.util.io.FileUtil;

public class FileTest {
	public static void uploadTest() throws Exception {
		String path = "img10.sharemyvision.com/minh";
		//File file = new File("C:\\Dev\\resources\\FriendsWithKids.jpg");
		File file = new File("C:\\Dev\\resources\\colosseum.jpg");
		
    	InputStream is = new BufferedInputStream (new FileInputStream (file));
    	
    	ItemDTO item = new ItemDTO();
		item.setFileName(file.getName());
		item.setFileSize(file.length());
		item.setPath(path);
		
		EventDTO event = new EventDTO();
		event.setItems(new ItemDTO[] {item} );
		
		EventDTO result = FileHelperS3UrlSigner.getInstance().signUploadFileUrls(event);
		
		String putUrl = item.getUrl();
		putUrl = putUrl.replace("https", "http");
		System.out.println("Upload Url : " + putUrl);
		
		/*
		S3Bucket bucket = new S3Bucket(path);
        S3Object object = new S3Object(bucket, "Blossom4.jpg");
        object.setContentLength(file.length());
        object.setDataInputStream(is);
        S3Object putObject = new RestS3Service(null).putObjectWithSignedUrl(putUrl, object);
        
        System.out.println("  Object has been uploaded to S3: " + putObject.getKey());
		*/
	}
	
	
	public static void deleteTest() throws Exception {
		String path = "img1.sharemyvision.com/minh";
		ItemDTO item = new ItemDTO();
		item.setFileName("FriendsWithKids.jpg");
		item.setPath(path);
		
		EventDTO event = new EventDTO();
		event.setItems(new ItemDTO[] {item} );
		
		EventDTO result = FileHelperS3UrlSigner.getInstance().signDeleteFileUrl(event);
		String deleteUrl = item.getUrl();
		System.out.println("Delete Url : " + deleteUrl);
		
		
		new RestS3Service(null).deleteObjectWithSignedUrl(deleteUrl);
		
		System.out.println(" Object has been deleted");
	}
	
	public static void fileDetailTest() throws Exception {
		String path = "img1.sharemyvision.com/minh";
		ItemDTO item = new ItemDTO();
		item.setFileName("FriendsWithKids.jpg");
		item.setPath(path);
		
		EventDTO event = new EventDTO();
		event.setItems(new ItemDTO[] {item} );
		
		EventDTO result = FileHelperS3UrlSigner.getInstance().signGetFileDetailUrl(event);
		String headUrl = item.getUrl();
		System.out.println("FileDetail Url : " + item.getUrl());
		
		S3Object headObject = new RestS3Service(null).getObjectDetailsWithSignedUrl(headUrl);
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
	
	
	public static void downloadFileTest() throws Exception {
		ItemDTO item = new ItemDTO();
		item.setFileName("Blossom.jpg");
		item.setPath("img1.sharemyvision.com/minh");
		
		EventDTO event = new EventDTO();
		event.setItems(new ItemDTO[] {item} );
		
		EventDTO result = FileHelperS3UrlSigner.getInstance().signDownloadFileUrl(event);
		System.out.println("Download Url : " + item.getUrl());
		String getUrl = item.getUrl();
		
		S3Object dlObject = new RestS3Service(null).getObjectWithSignedUrl(getUrl);
		FileUtil.saveImage(dlObject.getDataInputStream(), new File("c:\\dev\\temp\\pic" + System.currentTimeMillis() + ".jpg"));
	}
	
	
	public static void main(String[] args) throws Exception  {
		uploadTest();
		//fileDetailTest();
		//downloadFileTest();
		//deleteTest();
	}
}
