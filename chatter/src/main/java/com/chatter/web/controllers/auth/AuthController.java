package com.chatter.web.controllers.auth;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chatter.exception.BusinessException;
import com.chatter.security.SecurityContext;
import com.chatter.security.domain.ChatterUserDetails;
import com.chatter.service.auth.IAuthService;
import com.chatter.service.chat.IChatService;
import com.chatter.web.domain.ChatRequestBean;
import com.chatter.web.domain.UserBean;

@Controller
@RequestMapping(value="/auth" )
public class AuthController {
	Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	IAuthService authService;
	
	@Autowired
	IChatService chatService;
	
	public IChatService getChatService() {
		return chatService;
	}

	public void setChatService(IChatService chatService) {
		this.chatService = chatService;
	}

	public IAuthService getAuthService() {
		return authService;
	}

	public void setAuthService(IAuthService authService) {
		this.authService = authService;
	}

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(@ModelAttribute("user") UserBean user){
		logger.debug("in home action");
		ChatterUserDetails currentUser = SecurityContext.getCurrentUser();
		List<ChatRequestBean> chatRequests;
		try {
			chatRequests = getChatService().getChatRequestsForUser(currentUser.getUserId());
			user.setChatRequestBeans(chatRequests);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "home";
	}
	
	@RequestMapping(value="/showLogin", method=RequestMethod.GET)
	public String showLogin(@ModelAttribute("user") UserBean userBean){
			
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(@ModelAttribute("user") UserBean userBean){
		return "login";
	}

	@RequestMapping(value="/showregister", method=RequestMethod.GET)
	public String showRegister(@ModelAttribute("user") UserBean userBean){
		return "showregister";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(@ModelAttribute("user") UserBean userBean){
		try {
			getAuthService().saveUser(userBean);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/chatter/auth/showLogin";
	}
	
	@RequestMapping(value="/searchusers", method=RequestMethod.GET)
	@ResponseBody
	public List<UserBean> searchUsers(@RequestParam("term") String searchTerm){
		logger.info("in search user:: Authcontroller :search term is {}",searchTerm);
		List<UserBean> users;
		try {
			users = getAuthService().getAllUsers(searchTerm);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.toString());
			users = new ArrayList<UserBean>();
		} 
		return users;
	}
	
	@RequestMapping(value="/friends", method=RequestMethod.GET)
	@ResponseBody
	public List<UserBean> getFriends(){
		logger.info("in friends:: Authcontroller");
		List<UserBean> users;
		try {
			ChatterUserDetails currentUser = SecurityContext.getCurrentUser();
			if(currentUser != null){
				users = getAuthService().getAllFriends(currentUser.getUserId());
				
				return users;
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.toString());
			users = new ArrayList<UserBean>();
		} 
		return null;
	}
}
