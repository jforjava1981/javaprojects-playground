/**
 * pacakge for all service layer methods for auth/user module
 */
package com.chatter.service.auth;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chatter.dao.auth.IAuthDAO;
import com.chatter.exception.BusinessException;
import com.chatter.security.SecurityContext;
import com.chatter.security.domain.ChatterUserDetails;
import com.chatter.service.domain.vo.Role;
import com.chatter.service.domain.vo.User;
import com.chatter.web.domain.UserBean;
import com.chatterconstants.ChatterConstants;
/**
 * implementation of {@link IAuthService}
 * @author shailesh
 *
 */
public class AuthService implements IAuthService {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
	/*
	 * instance of IAuthDAO instantiated when application starts through 
	 * Spring's bean wiring features
	 * this is used to call DAO layer methods from service layer to fetch VOs.
	 * please refer applicationContext-auth.xml to see how it is configured.
	 */
	private IAuthDAO authDAO;

	/**
	 * convert instance of {@link User} to instance of {@link UserBean}
	 * @param user - instance of {@link User} to be converted
	 * @return converted instance of {@link UserBean}
	 */
	private UserBean convertUserVOToUserBean(User user){
		UserBean userBean = new UserBean();
		try{
			BeanUtils.copyProperties(userBean, user);
		}catch(IllegalAccessException iae){
			iae.printStackTrace();
		}catch(InvocationTargetException ite){
			ite.printStackTrace();
		}
		return userBean;
	}
	
	/**
	 * convert instance of {@link UserBean} to instance of {@link User}
	 * @param user - instance of {@link UserBean} to be converted
	 * @return converted instance of {@link User}
	 */
	private void convertUserBeanToUserVO(UserBean userBean,User user){
		
		try{
			BeanUtils.copyProperties(user,userBean);
		}catch(IllegalAccessException iae){
			iae.printStackTrace();
		}catch(InvocationTargetException ite){
			ite.printStackTrace();
		}
	}
	
	public IAuthDAO getAuthDAO() {
		return authDAO;
	}

	public void setAuthDAO(IAuthDAO authDAO) {
		this.authDAO = authDAO;
	}
	/**
	 * implementation of {@link IAuthService#saveUser(UserBean userBean)}
	 * @see {@link IAuthService#saveUser(UserBean userBean)}
	 */
	@Override
	public Integer saveUser(UserBean userBean) {
		User user = new User();
		convertUserBeanToUserVO(userBean,user);
		Role role = getAuthDAO().getRole(ChatterConstants.CHATTER_ROLE_USER);
		Set<Role> roles = new HashSet<Role>();
		
		roles.add(role);
		user.setRoles(roles);
		
		role.getUsers().add(user);
		
		Integer userId = getAuthDAO().saveUser(user);
		return userId;
	}
	/**
	 * implementation of {@link IAuthService#getUser(String userId)}
	 * @see {@link IAuthService#getUser(String userId)}
	 */
	@Override
	public UserBean getUser(String userId) throws BusinessException {
		User user = getAuthDAO().getUserByUserId(userId);
		UserBean userBean = convertUserVOToUserBean(user);
		return userBean;
	}
	/**
	 * implementation of {@link IAuthService#isUserAdmin(String userId)}
	 * @see {@link IAuthService#isUserAdmin(String userId)}
	 */
	@Override
	public boolean isUserAdmin(String userId) throws BusinessException {
		return getAuthDAO().isUserAdmin(userId);
	}

	@Override
	public List<UserBean> getAllUsers(String searchTerm) throws BusinessException {
		
		ChatterUserDetails currentUser = SecurityContext.getCurrentUser();
		String currentUserId = currentUser.getUserId();
		
		List<User> users = getAuthDAO().getAllUsers(searchTerm,currentUserId);
		List<UserBean> userBeans = new ArrayList<UserBean>();
		for(User user : users){
			UserBean userBean = convertUserVOToUserBean(user);
			userBean.setValue(userBean.getUserId());
			userBean.setLabel(userBean.getUserName());
			userBeans.add(userBean);
		}
		return userBeans;
	}

	@Override
	public List<UserBean> getAllFriends(String userId) throws BusinessException {
		
		List<User> users = getAuthDAO().getAllFriends(userId);
		List<UserBean> userBeans = new ArrayList<UserBean>();
		for(User user : users){
			UserBean userBean = convertUserVOToUserBean(user);
			userBean.setValue(userBean.getUserId());
			userBean.setLabel(userBean.getUserName());
			userBeans.add(userBean);
		}
		return userBeans;
	}

	@Override
	public boolean markUserAsLoggedIn(UserBean userBean) throws BusinessException {
		// TODO Auto-generated method stub
		if(userBean == null || userBean.getUserId() == null){
			throw new BusinessException("User with ID NULL is passed OR User is NULL. No such User exists");
		}
		User user = getAuthDAO().getUserByUserId(userBean.getUserId());
		user.setLoggedIn(ChatterConstants.CHATTER_STATUS_Y);
		getAuthDAO().updateUser(user);
		
		return true;
	}

	@Override
	public boolean markUserAsLoggedIOut(UserBean userBean) throws BusinessException {
	
		if(userBean == null || userBean.getUserId() == null){
			throw new BusinessException("User with ID NULL is passed OR User is NULL. No such User exists");
		}
		User user = getAuthDAO().getUserByUserId(userBean.getUserId());
		user.setLoggedIn(ChatterConstants.CHATTER_STATUS_N);
		getAuthDAO().updateUser(user);
		
		return true;
	}
	
	
}
