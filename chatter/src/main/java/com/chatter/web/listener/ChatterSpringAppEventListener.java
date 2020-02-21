package com.chatter.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.chatter.exception.BusinessException;
import com.chatter.security.SecurityContext;
import com.chatter.security.domain.ChatterUserDetails;
import com.chatter.service.auth.IAuthService;
import com.chatter.web.domain.UserBean;

public class ChatterSpringAppEventListener implements ApplicationListener<ApplicationEvent>{
	
	private static Logger logger = LoggerFactory.getLogger(ChatterSpringAppEventListener.class);
	
	private IAuthService authService;
	
	public IAuthService getAuthService() {
		return authService;
	}

	public void setAuthService(IAuthService authService) {
		this.authService = authService;
	}

	
	private void sessionCreated(Authentication authentication) {
		
		IAuthService authService = getAuthService();
		ChatterUserDetails currentUser = (ChatterUserDetails)authentication.getPrincipal();//SecurityContext.getCurrentUser();
		String userId = currentUser.getUserId();

		try {
			if(userId != null){
				UserBean userBean = new UserBean();
				userBean.setUserId(userId);
				Boolean marked = authService.markUserAsLoggedIn(userBean);	
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			logger.error("Exception While retriving User in Session Listener :{}",e.toString());
			e.printStackTrace();
		}
		
		
		
	}

	private void sessionDestroyed(HttpSession session) {
		
		IAuthService authService = getAuthService();
		ChatterUserDetails currentUser = SecurityContext.getCurrentUser();
		String userId = currentUser.getUserId();

		try {
			if(userId != null){
				UserBean userBean = new UserBean();
				userBean.setUserId(userId);
				Boolean marked = authService.markUserAsLoggedIOut(userBean);	
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			logger.error("Exception While retriving User in Session Listener :{}",e.toString());
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {

		if (applicationEvent instanceof AuthenticationSuccessEvent) { // If event
																	// is a
																	// session
			Authentication auth = ((AuthenticationSuccessEvent)applicationEvent).getAuthentication();
																	// event
			sessionCreated(auth);

		} else if (applicationEvent instanceof HttpSessionDestroyedEvent) {
			HttpSession httpSession = ((HttpSessionDestroyedEvent) applicationEvent)
					.getSession(); // get session object
			sessionDestroyed(httpSession);	
		}

	}



}
