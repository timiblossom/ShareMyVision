package com.smv.service.file.helper.impl;

import org.apache.log4j.Logger;

import com.smv.common.bean.EventDTO;
import com.smv.common.bean.ItemDTO;
import com.smv.service.file.helper.IUrlSigner;
import com.smv.util.vendor.s3.SmvS3SignedUrl;
import com.smv.util.vendor.s3.SmvS3SignedUrl.FileInfo;

public class FileHelperS3UrlSigner implements IUrlSigner {
	private static final Logger LOGGER = Logger.getLogger(FileHelperS3UrlSigner.class);

	private static FileHelperS3UrlSigner instance = new FileHelperS3UrlSigner();
	
	
	private FileHelperS3UrlSigner() {}
	
	public static FileHelperS3UrlSigner getInstance() {
		return instance;
	}
	
	public EventDTO signUploadFileUrls(EventDTO event) throws Exception {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter signUploadFileUrls - EventDTO : " + event);
		}
		
		for(ItemDTO item : event.getItems())  {			
			String signedUrl = SmvS3SignedUrl.createSignedPutUrl(item.getPath(), 
					                                             createFileInfo(item.getFileName(), 
					                                            		        item.getFileSize().longValue()));
			
            item.setUrl(signedUrl);					                                          
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit signUploadFileUrls - EventDTO : " + event);
		}
		
		return event;
	}
	
	public EventDTO signDownloadFileUrl(EventDTO event) throws Exception {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter signDownloadFileUrl - EventDTO : " + event);
		}
		
		if (event == null)
			return null;
		
		if (event.getItems() == null) {
			return event;
		}
		
		for(ItemDTO item : event.getItems()) {
			item.setUrl(SmvS3SignedUrl.createSignedGetUrl(item.getPath(), item.getFileName()));
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit signDownloadFileUrl - EventDTO : " + event);
		}
		
		return event;
	}
	
	public EventDTO signDeleteFileUrl(EventDTO event) throws Exception {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter signDeleteFileUrl - EventDTO : " + event);
		}
		
		if (event == null)
			return null;
		
		if (event.getItems() == null) {
			return event;
		}
		
		for(ItemDTO item : event.getItems()) {
			item.setUrl(SmvS3SignedUrl.createdSignedDeleteUrl(item.getPath(), item.getFileName()));
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit signDeleteFileUrl - EventDTO : " + event);
		}
		
		return event;
	}
	
	public EventDTO signGetFileDetailUrl(EventDTO event) throws Exception {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter signGetFileDetailUrl - EventDTO : " + event);
		}
		
		if (event == null)
			return null;
		
		if (event.getItems() == null) {
			return event;
		}
		
		for(ItemDTO item : event.getItems()) {
			item.setUrl(SmvS3SignedUrl.createSignedHeadUrl(item.getPath(), item.getFileName()));
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit signGetFileDetailUrl - EventDTO : " + event);
		}
		
		return event;
	}
	
	private FileInfo createFileInfo(String fileName, long fileSize) {
		FileInfo info = new FileInfo();
		
		info.filename = fileName;
		info.fileLength = fileSize;
		
		return info;
	}
	
}
