/**
 * pacakge for all service layer methods for auth/user module
 */
package com.chatter.service.chat;

import java.util.List;

import org.hibernate.exception.DataException;

import com.chatter.exception.BusinessException;
import com.chatter.service.domain.vo.ChatRequest;
import com.chatter.web.domain.ChatMessage;
import com.chatter.web.domain.ChatRequestBean;
/**
 * interface listing all the service layer methods  for auth/user module 
 * @author shailesh
 *
 */
public interface IChatService {
	
	public int sendChatRequest(ChatMessage chatMessage) throws BusinessException;
	
	public List<ChatRequestBean> getChatRequestsForUser(String userId) throws BusinessException;
	
	public boolean acceptChatRequest(ChatMessage chatMessage) throws BusinessException;
	
	public boolean rejectChatRequest(ChatMessage chatMessage) throws BusinessException;
	
	public boolean createFriendShip(String userId1,String userId2) throws BusinessException;
	
}
