package com.smv.service.iprocessor.helper;

import java.awt.RenderingHints;
import java.awt.image.renderable.ParameterBlock;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.media.jai.JAI;
import javax.media.jai.OpImage;
import javax.media.jai.RenderedOp;

import org.apache.log4j.Logger;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;

import com.smv.common.Constant;
import com.smv.common.bean.EventDTO;
import com.smv.common.bean.ItemDTO;
import com.smv.common.exception.SmvServiceException;
import com.smv.service.iprocessor.config.ResourceManager;
import com.smv.util.io.FileUtil;
import com.sun.media.jai.codec.SeekableStream;

public class ImageConversion {
	static {
		System.setProperty("com.sun.media.jai.disableMediaLib", "true");
	} 

	private static final Logger LOGGER = Logger.getLogger(ImageConversion.class);
	
	/**
	 * The JAI.create action name for handling a stream.
	 */
	private static final String JAI_STREAM_ACTION = "stream";

	/**
	 * The JAI.create action name for handling a resizing using a subsample averaging technique.
	 */
	private static final String JAI_SUBSAMPLE_AVERAGE_ACTION = "SubsampleAverage";

	/**
	 * The JAI.create encoding format name for JPEG.
	 */
	private static final String JAI_ENCODE_FORMAT_JPEG = "JPEG";

	/**
	 * The JAI.create action name for encoding image data.
	 */
	private static final String JAI_ENCODE_ACTION = "encode";

	
	public static class ImageInfo {
		public int origWidth;
		public int origHeight;
		public int convertedWidth;
		public int convertedHeight;
		public byte[] convertedImage;
	}

	/**
	 * This method takes in an image as a byte array (currently supports GIF, JPG, PNG and
	 * possibly other formats) and
	 * resizes it to have a width no greater than the pMaxWidth parameter in pixels.
	 * It converts the image to a standard
	 * quality JPG and returns the byte array of that JPG image.
	 *
	 * @param pImageData
	 *                the image data.
	 * @param pMaxWidth
	 *                the max width in pixels, 0 means do not scale.
	 * @return the resized JPG image.
	 * @throws IOException
	 *                 if the image could not be manipulated correctly.
	 */
	private static ImageInfo resizeImageAsJPGByWidth(byte[] pImageData, int pMaxWidth) throws IOException {
		ImageInfo retVal = new ImageInfo();
		
		InputStream imageInputStream = new ByteArrayInputStream(pImageData);
		// read in the original image from an input stream
		SeekableStream seekableImageStream = SeekableStream.wrapInputStream(imageInputStream, true);
		RenderedOp originalImage = JAI.create(JAI_STREAM_ACTION, seekableImageStream);
		((OpImage) originalImage.getRendering()).setTileCache(null);
		int origImageWidth = originalImage.getWidth();
		
		retVal.origHeight = originalImage.getHeight();
		retVal.origWidth = originalImage.getWidth();
		retVal.convertedWidth = pMaxWidth;
		
		// now resize the image
		double scale = 1.0;
		if (pMaxWidth > 0 && origImageWidth > pMaxWidth) {
			scale = (double) pMaxWidth / originalImage.getWidth();
		}
		
        retVal.convertedImage = doConversion(originalImage, scale, scale, 0.0, 0.0);
		
		return retVal;
	}


	private static ImageInfo resizeImageAsJPGByWidth(InputStream imageInputStream, int pMaxWidth) throws IOException {
		ImageInfo retVal = new ImageInfo();
		
		// read in the original image from an input stream
		SeekableStream seekableImageStream = SeekableStream.wrapInputStream(imageInputStream, true);
		RenderedOp originalImage = JAI.create(JAI_STREAM_ACTION, seekableImageStream);
		((OpImage) originalImage.getRendering()).setTileCache(null);
		int origImageWidth = originalImage.getWidth();
		
		retVal.origHeight = originalImage.getHeight();
		retVal.origWidth = originalImage.getWidth();
		retVal.convertedWidth = pMaxWidth;
		
		// now resize the image
		double scale = 1.0;
		if (pMaxWidth > 0 && origImageWidth > pMaxWidth) {
			scale = (double) pMaxWidth / originalImage.getWidth();
		}
		
        retVal.convertedImage = doConversion(originalImage, scale, scale, 0.0, 0.0);
		
		return retVal;
	}
	
	
	private static ImageInfo resizeImageAsJPGByHeight(byte[] pImageData, int pMaxHeight) throws IOException {
		ImageInfo retVal = new ImageInfo();
		
		InputStream imageInputStream = new ByteArrayInputStream(pImageData);
		// read in the original image from an input stream
		SeekableStream seekableImageStream = SeekableStream.wrapInputStream(imageInputStream, true);
		RenderedOp originalImage = JAI.create(JAI_STREAM_ACTION, seekableImageStream);
		((OpImage) originalImage.getRendering()).setTileCache(null);
		int origImageHeight = originalImage.getHeight();
		
		retVal.origHeight = originalImage.getHeight();
		retVal.origWidth = originalImage.getWidth();
		retVal.convertedHeight = pMaxHeight;
		
		// now resize the image
		double scale = 1.0;
		if (pMaxHeight > 0 && origImageHeight > pMaxHeight) {
			scale = (double) pMaxHeight / origImageHeight;
		}
		
        retVal.convertedImage = doConversion(originalImage, scale, scale, 0.0, 0.0);
		
		return retVal;
		
	}


	private static ImageInfo resizeImageAsJPGByHeight(InputStream imageInputStream, int pMaxHeight) throws IOException {
		ImageInfo retVal = new ImageInfo();
		
		// read in the original image from an input stream
		SeekableStream seekableImageStream = SeekableStream.wrapInputStream(imageInputStream, true);
		RenderedOp originalImage = JAI.create(JAI_STREAM_ACTION, seekableImageStream);
		((OpImage) originalImage.getRendering()).setTileCache(null);
		int origImageHeight = originalImage.getHeight();
		
		retVal.origHeight = originalImage.getHeight();
		retVal.origWidth = originalImage.getWidth();
		retVal.convertedHeight = pMaxHeight;
		
		// now resize the image
		double scale = 1.0;
		if (pMaxHeight > 0 && origImageHeight > pMaxHeight) {
			scale = (double) pMaxHeight / origImageHeight;
		}
		
        retVal.convertedImage = doConversion(originalImage, scale, scale, 0.0, 0.0);
		
		return retVal;
	}
	
	
	private static ImageInfo resizeImageAsJPGByRatio(InputStream imageInputStream, int pMaxWidth, int pMaxHeight) throws IOException {
		ImageInfo retVal = new ImageInfo();
		
		// read in the original image from an input stream
		SeekableStream seekableImageStream = SeekableStream.wrapInputStream(imageInputStream, true);
		RenderedOp originalImage = JAI.create(JAI_STREAM_ACTION, seekableImageStream);
		((OpImage) originalImage.getRendering()).setTileCache(null);
		
		retVal.origHeight = originalImage.getHeight();
		retVal.origWidth = originalImage.getWidth();
		retVal.convertedWidth = pMaxWidth;
		
		// now resize the image
		double xScale = (double) pMaxWidth / retVal.origWidth;
		double yScale = (double) pMaxHeight / retVal.origHeight;
		double scale = 1.0;
		if (xScale >= yScale) { //use width as the scaling factor
			scale = yScale;
		} else {
			scale = xScale;
		}
		
		
        retVal.convertedImage = doConversion(originalImage, scale, scale, 0.0, 0.0);
		return retVal;
	}
	
	
	private static byte[] doConversion(RenderedOp originalImage, double xScale, double yScale, double xTranslation, double yTranslation) {
		ParameterBlock paramBlock = new ParameterBlock();
		paramBlock.addSource(originalImage); // The source image
		paramBlock.add(xScale); // The xScale
		paramBlock.add(yScale); // The yScale
		paramBlock.add(xTranslation); // The x translation
		paramBlock.add(yTranslation); // The y translation
		
		RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		RenderedOp resizedImage = JAI.create(JAI_SUBSAMPLE_AVERAGE_ACTION, paramBlock, qualityHints);
		
		// lastly, write the newly-resized image to an output stream, in a specific encoding
		ByteArrayOutputStream encoderOutputStream = new ByteArrayOutputStream();
		JAI.create(JAI_ENCODE_ACTION, resizedImage, encoderOutputStream, JAI_ENCODE_FORMAT_JPEG, null);
		
		// Export to Byte Array
        return encoderOutputStream.toByteArray();
	}

	public static boolean convertAndSaveImage(byte[] origImageData, int pMaxWidth, File outFile) throws IOException {
		ImageInfo newImageData = resizeImageAsJPGByWidth(origImageData, pMaxWidth);
		return FileUtil.saveImage(newImageData.convertedImage, outFile);
	}

	public static boolean convertAndSaveImage(InputStream instream, int pMaxWidth, File outFile) throws IOException {
		ImageInfo newImageData = resizeImageAsJPGByWidth(instream, pMaxWidth);
		return FileUtil.saveImage(newImageData.convertedImage, outFile);
	}


	//TODO: reuse the same inputstream so that we don't have to download twice the orig image
	public static boolean convertAndUploadImage(String s3Url, Long userId, 
			String path, String fileName, int pMaxWidth) throws IOException, S3ServiceException, SmvServiceException { 
		
		boolean retVal = true;
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter convertAndUploadImage - {s3Url = " + s3Url + ", userId = " + userId + 
					      ", path = " + path + ", fileName = " + fileName + ", pMaxWidth = " + pMaxWidth + "}");
		}
		
		RestS3Service s3Service = new RestS3Service(null);
		S3Object dlObject = s3Service.getObjectWithSignedUrl(s3Url);

		ImageInfo newImageData = resizeImageAsJPGByWidth(dlObject.getDataInputStream(), pMaxWidth);
		dlObject.closeDataInputStream();
		
		EventDTO event = new EventDTO();
		event.setAction(Constant.SIGN_INTERNAL_UPLOAD_URL);
		event.setUserId(userId);		

		ItemDTO item = new ItemDTO();
		item.setUserId(userId);

		item.setFileName(fileName.substring(0, fileName.indexOf('.')) + "-" + pMaxWidth + ".jpg");		
		item.setFileSize(new Long(newImageData.convertedImage.length));
		item.setPath(path);

		event.setItems(new ItemDTO[] {item});

		EventDTO signedUploadUrlEvent = ResourceManager.getFileProxyService().eventRequest(event);

		S3Bucket bucket = new S3Bucket(path);
		S3Object object = new S3Object(bucket, item.getFileName());
		object.setContentLength(newImageData.convertedImage.length);
		object.setDataInputStream(new ByteArrayInputStream(newImageData.convertedImage));
		s3Service.putObjectWithSignedUrl(signedUploadUrlEvent.getItems()[0].getUrl(), object);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit convertAndUploadImage - {s3Url = " + s3Url + ", userId = " + userId + 
				         ", path = " + path + ", fileName = " + fileName + ", pMaxWidth = " + pMaxWidth + "}");
		}
		
		return retVal;

	}


	public static boolean convertAndUploadImage(String s3Url, Long userId,  String path, String fileName, int[] widths, int[] heights) 
	throws IOException, S3ServiceException, SmvServiceException { 
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter convertAndUploadImage - {s3Url = " + s3Url + ", userId = " + userId + 
				      ", path = " + path + ", fileName = " + fileName + ", widths.length = " + widths.length + ", heights.length = " + heights.length + "}");
		}
		
		RestS3Service s3Service = new RestS3Service(null);
		S3Object dlObject = s3Service.getObjectWithSignedUrl(s3Url);

		EventDTO event = new EventDTO();
		event.setAction(Constant.SIGN_INTERNAL_UPLOAD_URL);
		event.setUserId(userId);
		
		List<ItemDTO> items = new ArrayList<ItemDTO>(widths.length);
		byte[][] imagesForWidth = new byte[widths.length][];
		byte[][] imagesForHeight = new byte[heights.length][];
		//byte[][] imagesForRatios = new byte[ratios.length][];
		       
		Arrays.sort(widths);
		
		//Do it for the largest one
		ImageInfo imgInfo = resizeImageAsJPGByWidth(dlObject.getDataInputStream(), widths[widths.length - 1]);
		imagesForWidth[widths.length - 1] = imgInfo.convertedImage;
		ItemDTO item = new ItemDTO();
		item.setUserId(userId);
		item.setFileName(fileName.substring(0, fileName.indexOf('.')) + "-" + widths[widths.length - 1] + ".jpg");		
		item.setFileSize(new Long(imagesForWidth[widths.length - 1].length));
		item.setPath(path);
		items.add(item);
		
		for(int i = widths.length - 2; i>=0; i--) {
			imagesForWidth[i] = resizeImageAsJPGByWidth(imagesForWidth[i+1], widths[i]).convertedImage;
			item = new ItemDTO();
			item.setUserId(userId);
			item.setFileName(fileName.substring(0, fileName.indexOf('.')) + "-" + widths[i] + ".jpg");
			item.setFileSize(new Long(imagesForWidth[i].length));
			item.setPath(path);
			items.add(item);
		}
		
		//Do it for the height - use the largest image from the width as the starting image
		for(int j = 0; j<heights.length; j++) {
			imagesForHeight[j] = resizeImageAsJPGByHeight(imagesForWidth[widths.length - 1], heights[j]).convertedImage;
			item = new ItemDTO();
			item.setUserId(userId);
			item.setFileName(fileName.substring(0, fileName.indexOf('.')) + "-h" + heights[j] + ".jpg");
			item.setFileSize(new Long(imagesForHeight[j].length));
			item.setPath(path);
			items.add(item);
		}
		
		//Do it for the ratio
		/*
		double origRatio = (double) imgInfo.origWidth / (double) imgInfo.origHeight;		
		for(int k = 0; k<ratios.length; k++) {
			int index = ratios[k].indexOf(Constant.RATIO_SEPERATOR);
			int width = Integer.parseInt(ratios[k].substring(0, index));
			int height = Integer.parseInt(ratios[k].substring(index+1));
			double thisRatio = (double) width / (double) height;
			if (origRatio >= thisRatio) { //use width as the scaling factor
				imagesForRatios[k] = resizeImageAsJPGByWidth(imagesForWidth[widths.length - 1], width).convertedImage;
			} else { //use height
				imagesForRatios[k] = resizeImageAsJPGByHeight(imagesForWidth[widths.length - 1], height).convertedImage;
			}
			
			item = new ItemDTO();
			item.setUserId(userId);
			item.setFileName(fileName.substring(0, fileName.indexOf('.')) + "-" + ratios[k] + ".jpg");
			item.setFileSize(new Long(imagesForRatios[k].length));
			item.setPath(path);
			items.add(item);
		}
		*/
		
		event.setItems(items.toArray(new ItemDTO[0]));
		
		EventDTO signedUploadUrlEvent = ResourceManager.getFileProxyService().eventRequest(event);

		byte[] image = null;
		for(ItemDTO signedItem : signedUploadUrlEvent.getItems()) {
			S3Bucket bucket = new S3Bucket(path);
			S3Object object = new S3Object(bucket, signedItem.getFileName());
			
			image = null;
			for(int i=0; i<widths.length; i++) {
				if (imagesForWidth[i].length == signedItem.getFileSize().longValue()) {					
					image = imagesForWidth[i];
					break;
				}
			}
			
			if (image == null) {
				for(int i=0; i<heights.length; i++) {
					if (imagesForHeight[i].length == signedItem.getFileSize().longValue()) {
						image = imagesForHeight[i];
						break;
					}
				}
			}
			
			object.setContentLength(signedItem.getFileSize());
			object.setDataInputStream(new ByteArrayInputStream(image));
			s3Service.putObjectWithSignedUrl(signedItem.getUrl(), object);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit convertAndUploadImage - {s3Url = " + s3Url + ", userId = " + userId + 
				         ", path = " + path + ", fileName = " + fileName + ", widths.length = " + widths.length + ", heights.length = " + heights.length + "}");
		}
		
		return true;

	}

	
	public static boolean convertAndUploadImage(String s3Url, Long userId,  String path, String fileName, int width, int height) 
	throws IOException, S3ServiceException, SmvServiceException { 
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Enter convertAndUploadImage - {s3Url = " + s3Url + ", userId = " + userId + 
				         ", path = " + path + ", fileName = " + fileName + ", width = " + width + ", height = " + height + "}");
		}
		
		RestS3Service s3Service = new RestS3Service(null);
		S3Object dlObject = s3Service.getObjectWithSignedUrl(s3Url);
		
		EventDTO event = new EventDTO();
		event.setAction(Constant.SIGN_INTERNAL_UPLOAD_URL);
		event.setUserId(userId);
		
		byte[] image = resizeImageAsJPGByRatio(dlObject.getDataInputStream(), width, height).convertedImage;
		ItemDTO item = new ItemDTO();
		item.setUserId(userId);
		item.setFileName(fileName.substring(0, fileName.indexOf('.')) + "-folder.jpg");
		item.setFileSize(new Long(image.length));
		item.setPath(path);
		
		event.setItems(new ItemDTO[] {item});
		EventDTO signedUploadUrlEvent = ResourceManager.getFileProxyService().eventRequest(event);
		ItemDTO signedItem = signedUploadUrlEvent.getItems()[0];
		
		S3Bucket bucket = new S3Bucket(path);
		S3Object object = new S3Object(bucket, signedItem.getFileName());
		object.setContentLength(signedItem.getFileSize());
		object.setDataInputStream(new ByteArrayInputStream(image));
		s3Service.putObjectWithSignedUrl(signedItem.getUrl(), object);
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Exit convertAndUploadImage - {s3Url = " + s3Url + ", userId = " + userId + 
				         ", path = " + path + ", fileName = " + fileName + ", width = " + width + ", height = " + height + "}");
		}
		
		return true;
	}

}
