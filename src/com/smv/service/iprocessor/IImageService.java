package com.smv.service.iprocessor;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.smv.common.bean.EventDTO;
import com.smv.common.exception.SmvServiceException;


/**
 * @author Minh Do
 * 10/07/2010
 */

@WebService
public interface IImageService {
	//public boolean convertAndUploadImage(@WebParam(name = "url") String s3Url, @WebParam(name = "width") int width) throws SmvServiceException;
	//public boolean convertAndUploadImages(@WebParam(name = "url") String s3Url, @WebParam(name = "widthList") int[] widthArray) throws SmvServiceException;
	
	public EventDTO processImageByRatio(@WebParam(name = "event") EventDTO event, @WebParam(name = "width") int width, @WebParam(name = "height") int height) throws SmvServiceException;	
	public EventDTO processImage(@WebParam(name = "event") EventDTO event) throws SmvServiceException;
}
