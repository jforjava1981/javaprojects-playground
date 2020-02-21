/**
 * package contains DAO layer classes and interfaces for User module and authorization module 
 */
package com.chatter.dao.chat;

import java.util.List;

import org.hibernate.exception.DataException;

import com.chatter.exception.BusinessException;
import com.chatter.service.domain.vo.ChatRequest;
import com.chatter.service.domain.vo.Role;
import com.chatter.service.domain.vo.User;
import com.chatter.web.domain.ChatRequestBean;
import com.chatter.web.domain.UserBean;
/**
 * represents all the methods related to authorisation module <br>
 * and chat module
 * @author shailesh
 *
 */
public interface IChatDAO {
	
	public int saveChatRequest(ChatRequest chatRequest) throws DataException;
	
	public List<ChatRequest> getChatRequestsForUser(String userId) throws DataException;
	
	public List<ChatRequest> getChatRequests(String recepient,String sender) throws DataException;
	
	public ChatRequest getChatRequest(Integer chatRequestId) throws DataException;
	
	public boolean deleteChatRequest(Integer chatRequestId) throws DataException;
}
