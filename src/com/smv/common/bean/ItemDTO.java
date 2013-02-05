package com.smv.common.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Minh Do
 * 03/2010
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ItemDTO")
public class ItemDTO {
	
	private Long itemId;
	private String action;
	private String itemTitle;
	private String itemCode;
	private String description;
	private String status;
	private String url;
	private String url1;
	private String url2;
	private String url3;
	private Boolean processDone;
	private String path;
	private String fileName;
	private String md5InHex;
	private String md5InBase64;
	private Long userId;
	private Long accountId;
	private Boolean isPublic;
	private Integer width;
	private Integer height;
	private String mimeType;
	private Long fileSize;
	private Integer duration;
	private Double latitude;
	private Double longitude;
	private Double altitude;
	private String location;
	private Long deviceId;
	private Long sequenceId;
    private Long eventId;
	private Long expiredOn;
	private Long postedOn;
	
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long id) {
		this.itemId = id;
	}
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	public String getItemTitle() {
		return itemTitle;
	}
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	public String getItemDescription() {
		return description;
	}
	public void setItemDescription(String description) {
		this.description = description;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setUrl1(String url1) {
		this.url1 = url1;
	}
	public String getUrl1() {
		return url1;
	}
	
	public void setUrl2(String url2) {
		this.url2 = url2;
	}
	public String getUrl2() {
		return url2;
	}
	public void setUrl3(String url3) {
		this.url3 = url3;
	}
	public String getUrl3() {
		return url3;
	}
	public void setProcessDone(Boolean processDone) {
		this.processDone = processDone;
	}
	public Boolean getProcessDone() {
		return processDone;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileName() {
		return fileName;
	}
	public String getMd5InHex() {
		return md5InHex;
	}
	public void setMd5InHex(String md5InHex) {
		this.md5InHex = md5InHex;
	}
	
	public String getMd5InBase64() {
		return md5InBase64;
	}
	public void setMd5InBase64(String md5InBase64) {
		this.md5InBase64 = md5InBase64;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long uid) {
		this.userId = uid;
	}
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long aid) {
		this.accountId = aid;
	}
	
	public Boolean isItemPublic() {
		return isPublic;
	}
	public void setItemPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}
	
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}
	public Double getAltitude() {
		return altitude;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLocation() {
		return location;
	}
	
	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	
	public Long getSequenceId() {
		return sequenceId;
	}
	public void setSequenceId(Long sequenceId) {
		this.sequenceId = sequenceId;
	}
	
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	
	public Long getExpiredOn() {
		return expiredOn;
	}
	public void setExpiredOn(Long expiredOn) {
		this.expiredOn = expiredOn;
	}
	
	public Long getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(Long postedOn) {
		this.postedOn = postedOn;
	}
	


}
