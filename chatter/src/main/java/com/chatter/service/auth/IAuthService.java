/**
 * pacakge for all service layer methods for auth/user module
 */
package com.chatter.service.auth;

import java.util.List;

import com.chatter.exception.BusinessException;
import com.chatter.service.domain.vo.User;
import com.chatter.web.domain.UserBean;
/**
 * interface listing all the service layer methods  for auth/user module 
 * @author shailesh
 *
 */
public interface IAuthService {
	/**
	 * create new {@link User}
	 * @param user instane of {@link UserBean}
	 * @return {@link Integer} primary key of new User created
	 * @throws BusinessException
	 */
	public Integer saveUser(UserBean user) throws BusinessException;
	/**
	 * fetch User by userId login credential
	 * @param userId - login credential for the user
	 * @return instance of {@link UserBean} with properties of User
	 * @throws BusinessException
	 */
	public UserBean getUser(String userId) throws BusinessException;
	/**
	 * check if User has role ADMIN
	 * @param userId - userId login credential of User
	 * @return true if User has ADMIN role assigned,false otherwise
	 * @throws BusinessException
	 */
	public boolean isUserAdmin(String userId) throws BusinessException;
	
	/**
	 * get all users
	 * @return list of all users
	 * @throws BusinessException
	 */
	public List<UserBean> getAllUsers(String searchTerm) throws BusinessException;
	
	/**
	 * 
	 * @param userId - id of the User whose friends are to fetched
	 * @return list of friends
	 * @throws BusinessException
	 */
	public List<UserBean> getAllFriends(String userId) throws BusinessException;
	
	/**
	 * marks user as loged in to the system
	 */
	public boolean markUserAsLoggedIn(UserBean user) throws BusinessException;
	
	
	/**
	 * marks user as loged out from the system
	 */
	public boolean markUserAsLoggedIOut(UserBean user) throws BusinessException;
	
}
