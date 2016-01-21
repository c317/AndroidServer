package com.gasinfo.util;

public class Item {
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getRequester() {
		return requester;
	}
	public void setRequester(String requester) {
		this.requester = requester;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public String getrequestTitle() {
		return requestTitle;
	}
	public void setrequestTitle(String requestTitle) {
		this.requestTitle = requestTitle;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public String getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}
	public int getStatus() {
		return status;
	}
	public void changeTime() {
		if(this.requestTime!=null) {
			int a= requestTime.lastIndexOf(":");
			requestTime= requestTime.substring(0, a);
		}
		if(this.responseTime!=null) {
			int a= responseTime.lastIndexOf(":");
			responseTime= responseTime.substring(0, a);
		}
	}
	public void setStatus(int status) {
		this.status = status;
	}
	private int itemId;
	private String requester;
	private String approver;
	private String requestTitle;
	private String department;
	private String requestTime;
	private boolean isRead;
	private String responseTime;
	private int status;
}
