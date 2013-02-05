package com.smv.service.file.db.dbobject;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.smv.util.db.AbstractVersionedDatedDO;

/**
 * @author Minh Do
 * 03/2010
 */

public class EventDO extends AbstractVersionedDatedDO {

	private String title;
	private String eventCode;
	private String description;
	private String folderUrl;
	private Long uid;
	private Long aid;
	private String tagText;
	private String status;
	private Boolean isPublic;
	private Date expiredOn;
	private Date postedOn;
	private Set<ItemDO> items = new HashSet<ItemDO>(0);

	public EventDO() {
		super();
	}

	public EventDO(Long eventId) {
		super();
		this.id = eventId;
	}

	public String getEventTitle() {
		return title;
	}

	public void setEventTitle(String title) {
		this.title = title;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public String getEventCode() {
		return eventCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFolderUrl(String folderUrl) {
		this.folderUrl = folderUrl;
	}

	public String getFolderUrl() {
		return folderUrl;
	}

	public String getTagText() {
		return tagText;
	}

	public void setTagText(String tagText) {
		this.tagText = tagText;
	}
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getAid() {
		return aid;
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
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

	public Set<ItemDO> getItems() {
		return this.items;
	}

	public void setItems(Set<ItemDO> items) {
		this.items = items;
	}

}
