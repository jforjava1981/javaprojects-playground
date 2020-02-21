package com.chatter.web.domain;

import java.util.Date;

public class BaseBean {
	
	protected Date createdDate;
	
	protected String createdDateStr;
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedDateStr() {
		return createdDateStr;
	}

	public void setCreatedDateStr(String createdDateStr) {
		this.createdDateStr = createdDateStr;
	}
	
}
