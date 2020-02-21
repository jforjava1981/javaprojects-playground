package com.chatter.dao.chat;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.exception.DataException;

import com.chatter.dao.auth.IAuthDAO;
import com.chatter.service.domain.vo.ChatRequest;
import com.chatter.service.domain.vo.Role;
import com.chatter.service.domain.vo.User;
import com.chatter.web.domain.ChatRequestBean;
import com.chatterconstants.ChatterConstants;

public class ChatDAO implements IChatDAO {
	
	/*
	 * hibernate sessionfactory instance. this is assigned to SessionFactory implementation
	 * when application initializes using Spring's bean wiring feature
	 * Please refer applicationContext-angle.xml to see bean wiring configuration 
	 */
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int saveChatRequest(ChatRequest chatRequest) throws DataException {
		// TODO Auto-generated method stub
		return (Integer)getSessionFactory().getCurrentSession().save(chatRequest);
	}

	@Override
	public List<ChatRequest> getChatRequestsForUser(String userId)
			throws DataException {
		String query = "from ChatRequest cr where cr.recipient.userId = :userId";
		List<ChatRequest> chatRequests = getSessionFactory().getCurrentSession().createQuery(query).setParameter("userId", userId).list();
		return chatRequests;
	}

	@Override
	public List<ChatRequest> getChatRequests(String recipient, String sender)
			throws DataException {
		String query = "from ChatRequest cr where cr.recipient.userId = :recipient and cr.sender.userId = :sender";
		List<ChatRequest> chatRequests = getSessionFactory().getCurrentSession().createQuery(query).setParameter("recipient", recipient).setParameter("sender", sender).list();
		return chatRequests;
	}

	@Override
	public ChatRequest getChatRequest(Integer chatRequestId)
			throws DataException {
		return (ChatRequest)sessionFactory.getCurrentSession().get(ChatRequest.class,chatRequestId);
	}

	@Override
	public boolean deleteChatRequest(Integer chatRequestId) throws DataException {
		
		String query = "delete from ChatRequest c where c.id =:chatRequestId";
		int updatedRows = sessionFactory.getCurrentSession().createQuery(query).setParameter("chatRequestId",chatRequestId).executeUpdate();
		
		if(updatedRows > 0){
			return true;
		}
		return false;
	}
	
	
}
