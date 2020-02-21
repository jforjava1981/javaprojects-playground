package com.chatter.web.domain;

import java.util.List;

import com.chatter.service.domain.vo.ChatRequest;


public class UserBean extends BaseBean {
	
	private String userId;
	
	private String userPassword;
	
	private String userFirstName;
	
	private String userLastName;
	
	private String userEmailId;

	private Boolean userDisabled;
	
	private String value;
	
	private String label;
	
	private String loggedIn;
	
	private List<ChatRequestBean> chatRequestBeans;
	
	public List<ChatRequestBean> getChatRequestBeans() {
		return chatRequestBeans;
	}

	public void setChatRequestBeans(List<ChatRequestBean> chatRequestBeans) {
		this.chatRequestBeans = chatRequestBeans;
	}

	public String getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(String loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getUserName() {
		return userFirstName + " " + userLastName;
	}

	public Boolean getUserDisabled() {
		return userDisabled;
	}

	public void setUserDisabled(Boolean userDisabled) {
		this.userDisabled = userDisabled;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

}
