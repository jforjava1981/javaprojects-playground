package com.chatter.dao.auth;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.exception.DataException;

import com.chatter.service.domain.vo.Role;
import com.chatter.service.domain.vo.User;
import com.chatter.web.domain.UserBean;
import com.chatterconstants.ChatterConstants;

public class AuthDAO implements IAuthDAO {
	
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
	/**
	 * implementation of {@link IAuthDAO#saveUser(User user)}
	 * @see {@link IAuthDAO#saveUser(User user)}
	 */
	@Override
	public Integer saveUser(User user) throws DataException {
		Integer userId = (Integer) sessionFactory.getCurrentSession()
				.save(user);
		return userId;
	}
	/**
	 * implementation of {@link IAuthDAO#getUserByUserId(String userId)}
	 * @see {@link IAuthDAO#getUserByUserId(String userId)}
	 */
	@Override
	public User getUserByUserId(String userId) {
		String query = "from User u where u.userId = :userId";
		return (User) sessionFactory.getCurrentSession().createQuery(query)
				.setString("userId", userId).uniqueResult();
	}
	/**
	 * implementation of {@link IAuthDAO#getRole(String roleName)}
	 * @see {@link IAuthDAO#getRole(String roleName)}
	 */
	@Override
	public Role getRole(String roleName) {
		String query = "from Role r where r.roleName = :roleName";
		return (Role)sessionFactory.getCurrentSession().createQuery(query)
		.setString("roleName", roleName).uniqueResult();
	}
	/**
	 * implementation of {@link IAuthDAO#updateUser(User user)}
	 * @see {@link IAuthDAO#updateUser(User user)}
	 */
	@Override
	public User updateUser(User user) throws DataException {
		User endUser = (User) sessionFactory.getCurrentSession().merge(user);
		return endUser;
	}
	/**
	 * implementation of {@link IAuthDAO#isUserAdmin(String userId)}
	 * @see {@link IAuthDAO#isUserAdmin(String userId)}
	 */
	@Override
	public boolean isUserAdmin(String userId) throws DataException {
		String query = "select count(u) from User u inner join u.roles r where r.roleName = :roleName " +
				" and u.userId = :userId";
		 Integer count = (Integer) sessionFactory.getCurrentSession().createQuery(query)
				.setParameter("userId", userId)
				.setParameter("roleName", ChatterConstants.CHATTER_ROLE_ADMIN).uniqueResult();
		return (count > 0 ? true : false);
	}
	/**
	 * implementation of {@link IAuthDAO#deleteUser(User user)}
	 * @see {@link IAuthDAO#deleteUser(User user)}
	 */
	@Override
	public void deleteUser(User user) throws DataException {
		sessionFactory.getCurrentSession().delete(user);
	}

	@Override
	public List<User> getAllUsers(String searchTerm,String currentUserId) throws DataException {
		String query = "from User u where u.userFirstName like :searchTerm " +
				" and u.userId != :currentUserId and u not in (select uf from User u1 inner join u1.friends uf where u1.userId = :currentUserId)";
		return getSessionFactory().getCurrentSession().createQuery(query)
				.setParameter("searchTerm", '%' + searchTerm + '%')
				.setParameter("currentUserId",currentUserId)
				.list();
	}

	@Override
	public List<User> getAllFriends(String userId) throws DataException {
		String query = "select u.friends from User u where  u.userId = :userId";
		return getSessionFactory().getCurrentSession().createQuery(query)
				.setParameter("userId",userId)
				.list();
	}
}
