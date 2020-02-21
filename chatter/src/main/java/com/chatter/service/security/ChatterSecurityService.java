package com.chatter.service.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.chatter.dao.auth.IAuthDAO;
import com.chatter.security.domain.ChatterUserDetails;
import com.chatter.service.domain.vo.Role;
import com.chatter.service.domain.vo.User;

public class ChatterSecurityService implements IChatterUserDetailsService{
	
	IAuthDAO authDAO;
	
	
	public IAuthDAO getAuthDAO() {
		return authDAO;
	}


	public void setAuthDAO(IAuthDAO authDAO) {
		this.authDAO = authDAO;
	}


	@Override
	public UserDetails loadUserByUsername(String userId)
			throws UsernameNotFoundException {
		User user = getAuthDAO().getUserByUserId(userId);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username OR password.");
		}

		Set<Role> roles = user.getRoles();
		String[] rolesArr = new String[roles.size()];
		int index = 0;
		for (Role role : roles) {
			rolesArr[index] = "ROLE_" + role.getRoleName();
			index++;
		}
		Collection<? extends GrantedAuthority> authorities = AuthorityUtils
				.createAuthorityList(rolesArr);
		return new ChatterUserDetails(user.getUserId(), user.getUserPassword(),
				user.getUserFirstName(), user.getUserLastName(),
				user.getUserEmailId(), authorities, Arrays.asList(rolesArr));
	}
}
