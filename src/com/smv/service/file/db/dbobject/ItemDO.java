package com.smv.service.file.db.dbobject;

import java.util.Date;

import com.smv.util.db.AbstractVersionedDatedDO;

/**
 * @author Minh Do
 * 03/2010
 */
public class ItemDO extends AbstractVersionedDatedDO {

	private String itemTitle;
	private String itemCode;
	private String description;
	private String status;
	private Long uid;
	private Long aid;
	private Boolean isPublic;
	private Integer width;
	private Integer height;
	private String mimeType;
	private Long size;
	private Integer duration;
	private Double latitude;
	private Double longitude;
	private Double altitude;
	private String location;
	private Long deviceId;
	private String storageId;
	private String storageId1;
	private String storageId2;
	private String storageId3;
	private Boolean processDone;
	private Long sequenceId;
    private Long eventId;
	private Date expiredOn;
	private Date postedOn;

	public ItemDO() {
		super();
	}

	public ItemDO(Long itemId) {
		super();
		this.id = itemId;
	}



	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getUid() {
		return this.uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getAid() {
		return this.aid;
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}
	
	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public Long getEventId() {
		return this.eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Integer getWidth() {
		return this.width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return this.height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getMimeType() {
		return mimeType;
	}

	public Long getSize() {
		return this.size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Integer getDuration() {
		return this.duration;
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

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId1(String storageId1) {
		this.storageId1 = storageId1;
	}

	public String getStorageId1() {
		return storageId1;
	}

	public void setStorageId2(String storageId2) {
		this.storageId2 = storageId2;
	}

	public String getStorageId2() {
		return storageId2;
	}

	public void setStorageId3(String storageId3) {
		this.storageId3 = storageId3;
	}

	public String getStorageId3() {
		return storageId3;
	}

	public void setProcessDone(Boolean processDone) {
		this.processDone = processDone;
	}

	public Boolean getProcessDone() {
		return processDone;
	}

	public Long getSequenceId() {
		return this.sequenceId;
	}

	public void setSequenceId(Long sequenceId) {
		this.sequenceId = sequenceId;
	}

	public void setExpiredOn(Date expiredOn) {
		this.expiredOn = expiredOn;
	}

	public Date getExpiredOn() {
		return expiredOn;
	}

	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

	public Date getPostedOn() {
		return postedOn;
	}

}
