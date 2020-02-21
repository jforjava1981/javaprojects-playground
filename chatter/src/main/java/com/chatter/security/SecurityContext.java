package com.chatter.security;

import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.chatter.security.domain.ChatterUserDetails;

public class SecurityContext {
	
	public static ChatterUserDetails getCurrentUser(){
		Object principal = SecurityContextHolder.getContext().getAuthentication() == null ? null :SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal == null){
			return  null;
		}
		if(principal instanceof ChatterUserDetails){
 			return (ChatterUserDetails) principal;
		}
		ChatterUserDetails anonymousJkWebUserDetails = new ChatterUserDetails("anonymous","anonymous",new ArrayList<GrantedAuthority>());
		anonymousJkWebUserDetails.setAnonymous(true);
		return anonymousJkWebUserDetails;
	}
}
