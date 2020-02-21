package com.chatter.web.domain;

import com.chatter.service.domain.vo.User;

public class ChatRequestBean {
	
	private Integer id;
	
	private UserBean sender;
	
	private UserBean recipient;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserBean getSender() {
		return sender;
	}

	public void setSender(UserBean sender) {
		this.sender = sender;
	}

	public UserBean getRecipient() {
		return recipient;
	}

	public void setRecipient(UserBean recipient) {
		this.recipient = recipient;
	}




}
