package com.smv.service.iprocessor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Object;

import com.smv.common.Constant;
import com.smv.common.bean.EventDTO;
import com.smv.common.bean.ItemDTO;
import com.smv.common.bean.KeyValueEntryDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;
import com.smv.service.iprocessor.helper.ImageConversion;
import com.smv.service.iprocessor.helper.UrlHelper;


public class ImageServiceImpl implements IImageService {
	private static final Logger LOGGER = Logger.getLogger(ImageServiceImpl.class);

	public boolean convertAndUploadImage(String s3Url, int width) throws SmvServiceException {
				
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter convertAndUploadImage - {s3Url = " + s3Url + ", width = " + width + "}");
		}
		
		if (s3Url == null) {
			throw new SmvServiceException(SmvErrorCode.IP_NULL_URL_INPUT_EXCEPTION, SmvErrorCode.IP_NULL_URL_INPUT_EXCEPTION_MSG);
		}
		
		if (width <= 0) {
			throw new SmvServiceException(SmvErrorCode.IP_NEGATIVE_OR_ZERO_IMAGE_WIDTH_EXCEPTION, SmvErrorCode.IP_NEGATIVE_OR_ZERO_IMAGE_WIDTH_EXCEPTION_MSG);
		}
		
		try {
			S3Object dlObject = new RestS3Service(null).getObjectWithSignedUrl(s3Url);
			dlObject.getContentLength();
			
			ImageConversion.convertAndSaveImage(dlObject.getDataInputStream(), width, 
					                           new File(System.getProperty("java.io.tmpdir") + 
							                            System.getProperty("file.separator") + 
							                            "piccu" + System.currentTimeMillis() + ".jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit convertAndUploadImage - {s3Url = " + s3Url + ", width = " + width + "}");
		}
		
		return false;
	}


	@Override
	public EventDTO processImage(EventDTO event) throws SmvServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter processImage - {EventDTO = " + event + "}");
		}
		
		if (event == null) {
			throw new SmvServiceException(SmvErrorCode.IP_NULL_EVENT_ARGUMENT_EXCEPTION, SmvErrorCode.IP_NULL_EVENT_ARGUMENT_EXCEPTION_MSG);
		}

		if (event.getUserId() == null) {
			throw new SmvServiceException(SmvErrorCode.IP_NULL_USERID_IN_EVENT_EXCEPTION, SmvErrorCode.IP_NULL_USERID_IN_EVENT_EXCEPTION_MSG);
		}
		
		if (event.getPolicy() == null) {
			throw new SmvServiceException(SmvErrorCode.IP_NULL_POLICIES_IN_EVENT_EXCEPTION, SmvErrorCode.IP_NULL_POLICIES_IN_EVENT_EXCEPTION_MSG);
		} else {
			if (event.getPolicy().getEntries() == null) {
				throw new SmvServiceException(SmvErrorCode.IP_POLICY_MISSING_ENTRIES_EXCEPTION, SmvErrorCode.IP_POLICY_MISSING_ENTRIES_EXCEPTION_MSG);				
			}
		}

		if (event.getItems() == null) {
			throw new SmvServiceException(SmvErrorCode.IP_NULL_ITEM_IN_EVENT_EXCEPTION, SmvErrorCode.IP_NULL_ITEM_IN_EVENT_EXCEPTION_MSG);
		} else {
			for(ItemDTO item : event.getItems()) {
				if (item.getUrl() == null) {
					throw new SmvServiceException(SmvErrorCode.IP_ITEM_MUST_INCLUDE_URL_VALUE_EXCEPTION, SmvErrorCode.IP_ITEM_MUST_INCLUDE_URL_VALUE_EXCEPTION_MSG); 			
				}
			}
		}		
		
		try {
			List<KeyValueEntryDTO> entries = event.getPolicy().getEntries();			

			Map<String, String> policyMap = new HashMap<String, String>();
			int numThumbnailsForWidth = 0;
			int numThumbnailsForHeight = 0;
			
			for(KeyValueEntryDTO entry : entries) {
				policyMap.put(entry.getKey(), entry.getValue());
				if (Constant.NUM_THUMBNAILS_FOR_WIDTH.equalsIgnoreCase(entry.getKey())) {
					numThumbnailsForWidth = Integer.parseInt(entry.getValue());
				} else if (Constant.NUM_THUMBNAILS_FOR_HEIGHT.equalsIgnoreCase(entry.getKey())) {
					numThumbnailsForHeight = Integer.parseInt(entry.getValue());
				}
			}
			int[] widths = new int[numThumbnailsForWidth];
			for(int i=1; i<=numThumbnailsForWidth; i++) {				
				widths[i-1] = Integer.parseInt(policyMap.get(Constant.THUMBNAIL_WIDTH_PREFIX + i));
			}
			
			int[] heights = new int[numThumbnailsForHeight];
			for(int i=1; i<=numThumbnailsForHeight; i++) {				
				heights[i-1] = Integer.parseInt(policyMap.get(Constant.THUMBNAIL_HEIGHT_PREFIX + i));
			}
						
			for(ItemDTO item : event.getItems()) {				
				String[] ss = UrlHelper.getPathAndFileName(item.getUrl());
				ss[0] = ss[0] + "/a";
								
				ImageConversion.convertAndUploadImage(item.getUrl(), event.getUserId(), ss[0], ss[1], widths, heights);
			}
			
		} catch (SmvServiceException e) {
			throw e;
		} catch (Exception e) { //TODO: convert to SmvServiceException
			LOGGER.error(e);
			throw new SmvServiceException(SmvErrorCode.SYSTEM_UNKNOWN_EXCEPTION, SmvErrorCode.SYSTEM_UNKNOWN_EXCEPTION_MSG);
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit processImage - {EventDTO = " + event + "}");
		}
		
		return null;
	}

	public EventDTO processImageByRatio(EventDTO event, int width, int height) throws SmvServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter processImageByRatio - {width = " + width + ", height = " + height + "}");
		}
		
		if (event == null) {
			throw new SmvServiceException(SmvErrorCode.IP_NULL_EVENT_ARGUMENT_EXCEPTION, SmvErrorCode.IP_NULL_EVENT_ARGUMENT_EXCEPTION_MSG);
		}

		if (event.getUserId() == null) {
			throw new SmvServiceException(SmvErrorCode.IP_NULL_USERID_IN_EVENT_EXCEPTION, SmvErrorCode.IP_NULL_USERID_IN_EVENT_EXCEPTION_MSG);
		}
		
		if (width <= 0 || height <= 0) {
			throw new SmvServiceException(SmvErrorCode.IP_NON_POSITIVE_IMAGE_DIMENSION_EXCEPTION, SmvErrorCode.IP_NON_POSITIVE_IMAGE_DIMENSION_EXCEPTION_MSG);
		}
		
		if (event.getItems() == null) {
			throw new SmvServiceException(SmvErrorCode.IP_NULL_ITEM_IN_EVENT_EXCEPTION, SmvErrorCode.IP_NULL_ITEM_IN_EVENT_EXCEPTION_MSG); 			
		} else {
			for(ItemDTO item : event.getItems()) {
				if (item.getUrl() == null) {
					throw new SmvServiceException(SmvErrorCode.IP_ITEM_MUST_INCLUDE_URL_VALUE_EXCEPTION, SmvErrorCode.IP_ITEM_MUST_INCLUDE_URL_VALUE_EXCEPTION_MSG); 			
				}
			}
		}				
		
		ItemDTO item = event.getItems()[0];
		String[] ss = UrlHelper.getPathAndFileName(item.getUrl());
		ss[0] = ss[0] + "/a";
		
		try {
			ImageConversion.convertAndUploadImage(item.getUrl(), event.getUserId(), ss[0], ss[1], width, height);
		} catch (IOException e) { //TODO: convert to SmvServiceException
			LOGGER.error(e);
			//TODO: add throwing SmvServiceException
		} catch (S3ServiceException e) {
			LOGGER.error(e);
			//TODO: add throwing SmvServiceException
		}		
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit processImageByRatio - {width = " + width + ", height = " + height + "}");
		}
		return null;
	}


}
