package com.smv.common.bean;

/**
 * @author Minh Do
 * 03/2010
 */
public class EventDTO {
	private String action;
	private Long eventId;
	private String eventTitle;
	private String eventCode;
	private String eventDescription;
	private String folderUrl;
	private Long userId;
	private Long accountId;
	private String tagText;
	private String eventStatus;
	private Boolean isPublic;
	private Long expiredOn;
	private Long postedOn;
	private ItemDTO[] items;
	private KeyValueMapDTO policy;
	private Boolean isNew;
	

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getEventId() {
		return eventId;
	}
	
	public void setAction(String action) {
		this.action = action;
	}

	public String getAction() {
		return action;
	}
	
	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String title) {
		this.eventTitle = title;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public String getEventCode() {
		return eventCode;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String description) {
		this.eventDescription = description;
	}

	public void setFolderUrl(String folderUrl) {
		this.folderUrl = folderUrl;
	}

	public String getFolderUrl() {
		return folderUrl;
	}

	public String getEventTagText() {
		return tagText;
	}

	public void setEventTagText(String tagText) {
		this.tagText = tagText;
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

	public String getStatus() {
		return eventStatus;
	}

	public void setStatus(String status) {
		this.eventStatus = status;
	}

	public Boolean isEventPublic() {
		return isPublic;
	}

	public void setEventPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public void setExpiredOn(Long expiredOn) {
		this.expiredOn = expiredOn;
	}

	public Long getExpiredOn() {
		return expiredOn;
	}

	public void setPostedOn(Long postedOn) {
		this.postedOn = postedOn;
	}

	public Long getPostedOn() {
		return postedOn;
	}
	
	public void setItems(ItemDTO[] items) {
		this.items = items;
	}

	public ItemDTO[] getItems() {
		return items;
	}

	public void setPolicy(KeyValueMapDTO policy) {
		this.policy = policy;
	}

	public KeyValueMapDTO getPolicy() {
		return policy;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	public Boolean getIsNew() {
		return isNew;
	}
	

}
