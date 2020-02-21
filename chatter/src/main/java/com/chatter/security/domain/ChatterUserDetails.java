package com.chatter.security.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class ChatterUserDetails extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1090490236599252237L;

	private String userEmailId;
	
	private String firstName;
	
	private String lastName;
	
	private String userId;
	
	private boolean isAnonymous;
	
	private List<String> roles;
	
	public ChatterUserDetails(String userId,String userPassword,Collection<? extends GrantedAuthority> authorities){
		super(userId,userPassword,authorities);
		this.isAnonymous = false;
	}
	
	public ChatterUserDetails(String userId,String userPassword,String firstName,String lastName, String emailId,Collection<? extends GrantedAuthority> authorities, List<String> roles){
		super(userId,userPassword,authorities);
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userEmailId = emailId;
		this.isAnonymous = false;
		this.roles = roles;
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return super.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public boolean isAnonymous() {
		return isAnonymous;
	}

	public void setAnonymous(boolean isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	

}
