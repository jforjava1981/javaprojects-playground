/**
 * pacakge for all service layer methods for auth/user module
 */
package com.chatter.service.chat;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.chatter.dao.auth.IAuthDAO;
import com.chatter.dao.chat.IChatDAO;
import com.chatter.exception.BusinessException;
import com.chatter.service.domain.vo.ChatRequest;
import com.chatter.service.domain.vo.User;
import com.chatter.web.domain.ChatMessage;
import com.chatter.web.domain.ChatRequestBean;
import com.chatter.web.domain.UserBean;
/**
 * implementation of {@link IAuthService}
 * @author shailesh
 *
 */
/**
 * @author shailesh
 *
 */

public class ChatService implements IChatService {
	
	private static final Logger logger = LoggerFactory.getLogger(ChatService.class);
	
	/*
	 * instance of IAuthDAO instantiated when application starts through 
	 * Spring's bean wiring features
	 * this is used to call DAO layer methods from service layer to fetch VOs.
	 * please refer applicationContext-auth.xml to see how it is configured.
	 */
	private IAuthDAO authDAO;
	
	private IChatDAO chatDAO;
	
	public IChatDAO getChatDAO() {
		return chatDAO;
	}

	public void setChatDAO(IChatDAO chatDAO) {
		this.chatDAO = chatDAO;
	}

	public IAuthDAO getAuthDAO() {
		return authDAO;
	}

	public void setAuthDAO(IAuthDAO authDAO) {
		this.authDAO = authDAO;
	}

	@Override
	public int sendChatRequest(ChatMessage chatMessage) throws BusinessException {
		String senderUserId = chatMessage.getMsender();
		String recipientUserId = chatMessage.getRecepient();
		
		
		List<ChatRequest> chatRequests = getChatDAO().getChatRequests(recipientUserId, senderUserId);
		
		if(chatRequests == null || chatRequests.size() == 0){
			User sender = getAuthDAO().getUserByUserId(senderUserId);
			User reciepient = getAuthDAO().getUserByUserId(recipientUserId);
			
			ChatRequest chatRequest  = new ChatRequest();
			chatRequest.setSender(sender);
			chatRequest.setRecipient(reciepient);
			
			reciepient.getChatRequests().add(chatRequest);
			
			int chatRequestId = getChatDAO().saveChatRequest(chatRequest);
			return chatRequestId;
		}
		return 0;
	}

	@Override
	public List<ChatRequestBean> getChatRequestsForUser(String userId)
			throws BusinessException {
		List<ChatRequest> chatReuqests = getChatDAO().getChatRequestsForUser(userId);
		List<ChatRequestBean> chatReuqestBeans = new ArrayList<ChatRequestBean>();
		for (ChatRequest chatRequest : chatReuqests) {
			ChatRequestBean chatRequestBean = new ChatRequestBean();
			chatRequestBean.setId(chatRequest.getId());
			
			User sender = chatRequest.getSender();
			UserBean senderBean = new UserBean();
			senderBean.setUserId(sender.getUserId());
			senderBean.setUserFirstName(sender.getUserFirstName());
			senderBean.setUserLastName(sender.getUserLastName());
			
			chatRequestBean.setSender(senderBean);
			chatReuqestBeans.add(chatRequestBean);
		}
		return chatReuqestBeans;
	}

	@Override
	public boolean acceptChatRequest(ChatMessage chatMessage)
			throws BusinessException {
		String senderUserId = chatMessage.getMsender();
		String recipientUserId = chatMessage.getRecepient();
		Integer chatRequestId = chatMessage.getRequestId();
		if(senderUserId == null || recipientUserId == null || chatRequestId == null){
			return false;
		}
				
		boolean requestDeleted = getChatDAO().deleteChatRequest(chatRequestId);
		boolean friendShipCreated = createFriendShip(recipientUserId,senderUserId);
		
		
		if(requestDeleted && friendShipCreated){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean rejectChatRequest(ChatMessage chatMessage)
			throws BusinessException {
		Integer charRequestId = chatMessage.getRequestId();
		if(charRequestId == null){
			return false;
		}
				
		boolean requestDeleted = getChatDAO().deleteChatRequest(charRequestId);
		if(requestDeleted){
			return true;
		}
		return false;
	}

	@Override
	public boolean createFriendShip(String  recipientUserId, String senderUserId)
			throws BusinessException {
		
		User sender = getAuthDAO().getUserByUserId(senderUserId);
		User recipient = getAuthDAO().getUserByUserId(recipientUserId);
		sender.getFriends().add(recipient);
		recipient.getFriends().add(sender);
		getAuthDAO().updateUser(sender);
		getAuthDAO().updateUser(recipient);
		return true;
	}
	
	
}
