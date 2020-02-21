package com.chatter.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatMessage {

	private String recepient;
	
	private String message;
	
	private String type;
	
	private String msender;
	
	private String status;
	
	private UserBean userInfo;
	
	private Integer requestId;
	
		
	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public UserBean getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserBean userInfo) {
		this.userInfo = userInfo;
	}

	public String getStatus() {
		return status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsender() {
		return msender;
	}

	@JsonProperty(value="msender")
	public void setMsender(String msender) {
		this.msender = msender;
	}

	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRecepient() {
		return recepient;
	}

	public void setRecepient(String recepient) {
		this.recepient = recepient;
	}

}
 