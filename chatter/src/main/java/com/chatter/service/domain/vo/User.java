package com.chatter.service.domain.vo;

import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * represents a user for the application
 * @author shailesh
 *
 */
public class User{
	/**
	 * primary key of the User
	 */
	private Integer id;
	/**
	 * unique id for the user used as login credential
	 */
	private String userId;
	/**
	 * password for the user used as login credential
	 */
	private String userPassword;
	/**
	 * First name of the user
	 */
	private String userFirstName;
	/**
	 * Last name of the user
	 */
	private String userLastName;
	/**
	 * Email Id of the user
	 */
	private String userEmailId;
	
	/**
	 * roles this user has
	 */
	private Set<Role> roles;
	
	/**
	 * 
	 */
	private Set<User> friends;
	
	private String loggedIn;
	
	private Set<ChatRequest> chatRequests;
	
	public Set<ChatRequest> getChatRequests() {
		return chatRequests;
	}

	public void setChatRequests(Set<ChatRequest> chatRequests) {
		this.chatRequests = chatRequests;
	}

	public String getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(String loggedIn) {
		this.loggedIn = loggedIn;
	}

	public Set<User> getFriends() {
		return friends;
	}

	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/**
	 * equal method implementation<br>
	 * two Users will be equal if and only if 
	 * their userIds are same  
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}
		if(!(obj instanceof User)){
			return false;
		}
		User objUser = (User)obj;
		return new EqualsBuilder().
	            // if deriving: appendSuper(super.equals(obj)).
	            append(this.getUserId(),objUser.getUserId()).
	            isEquals();
	}
	/**
	 * hash code implementation which returns hash code based 
	 * on user id
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
	            // if deriving: appendSuper(super.hashCode()).
	            append(this.getUserId()).
	            toHashCode();
	}
}
