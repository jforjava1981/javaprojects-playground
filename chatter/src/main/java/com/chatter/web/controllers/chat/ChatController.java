package com.chatter.web.controllers.chat;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chatter.exception.BusinessException;
import com.chatter.service.auth.IAuthService;
import com.chatter.service.chat.IChatService;
import com.chatter.web.domain.ChatMessage;
import com.chatter.web.domain.UserBean;

/**
 * @author shailesh
 *
 */
@Controller
@RequestMapping(value="/chat" )
public class ChatController {

	private static Logger logger = LoggerFactory.getLogger(ChatController.class);
	

	private SimpMessagingTemplate template;
	
	@Autowired
	public ChatController(SimpMessagingTemplate template){
		this.template = template;
	}
	
	@Autowired
	private IAuthService authService;
	
	@Autowired
	private IChatService chatService;
	
	public IChatService getChatService() {
		return chatService;
	}

	public void setChatService(IChatService chatService) {
		this.chatService = chatService;
	}
	
	/*@SubscribeMapping(value="/topic/logintracker")
	@SendTo(value="/topic/logintracker")	
	public void trackChatLogin(Principal principal, @Payload ChatMessage chatMessage){
		logger.info("Client {} subscribed to login tracker topic",principal.getName());
		((ChatMessage)chatMessage).setUserId(principal.getName());
		template.convertAndSend("/topic/logintracker",chatMessage);
		//return chatMessage;
	}*/
	
	/*@MessageMapping(value="/messaging")
	@SendTo(value="/queue/mesaging")
	public String message(Principal principal,Message<ChatMessage> chatMessage){
		logger.info("{} sent message :{} to {}",principal.getName(),chatMessage.getPayload().getMessage(),chatMessage.getPayload().getRecepient());
		template.convertAndSendToUser(chatMessage.getPayload().getRecepient(), "/user/queue/mesaging",chatMessage); 		
		return "delivered";
	}*/


	public IAuthService getAuthService() {
		return authService;
	}

	public void setAuthService(IAuthService authService) {
		this.authService = authService;
	}

	@RequestMapping(value="/invite")
	@ResponseBody
	public String sendChatRequest(Principal principal,@ModelAttribute ChatMessage chatMessage){
		logger.info("{} sent chat request to {}",principal.getName(),chatMessage.getRecepient());
		try {
			int requestId = chatService.sendChatRequest(chatMessage);
			if(requestId > 0){
				UserBean user = getAuthService().getUser(chatMessage.getMsender());
				chatMessage.setUserInfo(user);
				chatMessage.setRequestId(requestId);
				template.convertAndSendToUser(chatMessage.getRecepient(), "/queue/messaging",chatMessage);
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return "request sent";
	}
	
	@RequestMapping(value="/acceptinvite")
	@ResponseBody
	public String acceptChatRequest(Principal principal,@ModelAttribute ChatMessage chatMessage){
		logger.info("{chat request by {} accepted by {}",chatMessage.getMsender(),principal.getName());
		try {
			boolean chatRequestAccepted = chatService.acceptChatRequest(chatMessage);
			if(chatRequestAccepted){
				UserBean userRecipient = getAuthService().getUser(chatMessage.getRecepient());
				chatMessage.setUserInfo(userRecipient);
				template.convertAndSendToUser(chatMessage.getMsender(), "/queue/messaging",chatMessage);
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return "request accepted";
	}
	
	@RequestMapping(value="/rejectinvite")
	@ResponseBody
	public String rejectChatRequest(Principal principal,@ModelAttribute ChatMessage chatMessage){
		logger.info("{chat request by {} accepted by {}",principal.getName(),chatMessage.getRecepient());
		try {
			boolean requestRejected = chatService.rejectChatRequest(chatMessage);
			if(requestRejected){
				return "request rejected";
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return "not rejected";
	}
	
}
