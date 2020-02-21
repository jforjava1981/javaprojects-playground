package com.chatter.service.domain.vo;

import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.security.core.GrantedAuthority;
/**
 * Role of every type of User in the application
 * @author shailesh
 *
 */
public class Role implements GrantedAuthority{
	/**
	 * primary key of id - persistent field
	 */
	private Integer id;
	/**
	 * name of the role - persistent field
	 */
	private String roleName;
	/**
	 * description for role
	 */
	private String roleDescription;
	/**
	 * list of users who have this role
	 */
	private Set<User> users;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	/**
	 * equal method implementation<br>
	 * two roles will be equal if and only if 
	 * their names are same 
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}
		if(!(obj instanceof Role)){
			return false;
		}
		Role objRole = (Role)obj;
		return new EqualsBuilder().
	            // if deriving: appendSuper(super.equals(obj)).
	            append(this.getRoleName(),objRole.getRoleName()).
	            isEquals();
	}
	/**
	 * hash code implementation which returns hash code based 
	 * on role name 
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
	            // if deriving: appendSuper(super.hashCode()).
	            append(this.getRoleName()).
	            toHashCode();
	}
	/**
	 * identifies authority identity in Spring Security infrastructure
	 * @return
	 */
	@Override
	public String getAuthority() {
		return this.roleName;
	}
	
}
