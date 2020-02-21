/**
 * package contains DAO layer classes and interfaces for User module and authorization module 
 */
package com.chatter.dao.auth;

import java.util.List;

import org.hibernate.exception.DataException;

import com.chatter.exception.BusinessException;
import com.chatter.service.domain.vo.Role;
import com.chatter.service.domain.vo.User;
import com.chatter.web.domain.ChatRequestBean;
import com.chatter.web.domain.UserBean;
/**
 * represents all the methods related to authorisation module <br>
 * and User module
 * @author shailesh
 *
 */
public interface IAuthDAO {
	/**
	 * saves the User
	 * @param user - represents instance of {@link User}
	 * @return {@link Integer} - primary key of the User that is created
	 * @throws DataException
	 */
	public Integer saveUser(User user) throws DataException;
	/**
	 * fetch user by its userId login credential
	 * @param userId - login credential
	 * @return instance of {@link User} if user with given userId is found else NULL 
	 * @throws DataException
	 */
	public User getUserByUserId(String userId) throws DataException;
	/**
	 * fetch the role by the role name
	 * @param roleName - name of the role
	 * @return instance of {@link Role} if role with given name is found else NULL
	 */
	public Role getRole(String roleName);
	/**
	 * updates the user
	 * @param user - instance of {@link User} which is being updated
	 * @return instance of {@link User} which is updated
	 * @throws DataException
	 */
	public User updateUser(User user) throws DataException;
	/**
	 * check if User has ADMIN role
	 * @param userId - id of the user 
	 * @return true if user has ADMIN role false otherwise
	 * @throws DataException
	 */
	public boolean isUserAdmin(String userId) throws DataException;
	/**
	 * deletes the user
	 * @param user - instance of {@link User} which is to be deleted 
	 * @throws DataException
	 */
	public void deleteUser(User user) throws DataException;
	
	/**
	 * get all users
	 * @return list of users
	 * @throws DataException
	 */
	public List<User> getAllUsers(String searchTerm,String currentUserId) throws DataException;
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws DataException
	 */
	public List<User> getAllFriends(String userId) throws DataException; 
	
}
