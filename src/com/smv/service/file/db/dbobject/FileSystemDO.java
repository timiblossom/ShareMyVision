package com.smv.service.file.db.dbobject;

import com.smv.util.db.AbstractVersionedDatedDO;


/**
 * @author Minh Do
 * 09/2010
 */
public class FileSystemDO extends AbstractVersionedDatedDO {
	private Long filePolicyId;
	private String server;
	private String fileId1;
	private String fileId2;
	private String fileId3;
	private long usedSpace;
	private int eventCount;
	private int fileCount;
	
	
	public void setFilePolicyId(Long filePolicyId) {
		this.filePolicyId = filePolicyId;
	}
	public Long getFilePolicyId() {
		return filePolicyId;
	}
	
	public void setServer(String server) {
		this.server = server;
	}
	public String getServer() {
		return server;
	}
	
	public String getFileId1() {
		return fileId1;
	}
	public void setFileId1(String fileId1) {
		this.fileId1 = fileId1;
	}
	
	public String getFileId2() {
		return fileId2;
	}
	public void setFileId2(String fileId2) {
		this.fileId2 = fileId2;
	}
	
	public String getFileId3() {
		return fileId3;
	}
	public void setFileId3(String fileId3) {
		this.fileId3 = fileId3;
	}
	
	public long getUsedSpace() {
		return usedSpace;
	}
	public void setUsedSpace(long usedSpace) {
		this.usedSpace = usedSpace;
	}
	
	public int getEventCount() {
		return eventCount;
	}
	public void setEventCount(int eventCount) {
		this.eventCount = eventCount;
	}
	
	public int getFileCount() {
		return fileCount;
	}
	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}
	
	
}

