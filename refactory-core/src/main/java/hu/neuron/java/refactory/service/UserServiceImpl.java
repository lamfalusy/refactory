package hu.neuron.java.refactory.service;

import hu.neuron.java.refactory.dao.UserDAOFactory;
import hu.neuron.java.refactory.entity.User;
import hu.neuron.java.refactory.vo.UserVO;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

	@Override
	public Long createUser(UserVO user) {		
		User entity = new User();
		
		entity.setEmail(user.getEmail());
		entity.setFullName(user.getFullName());
		entity.setLoginName(user.getLoginName());
		entity.setRole(user.getRole());
		entity.setPassword(user.getPassword());
		
		try {
			UserDAOFactory.getUserDao().insert(entity);
		} catch (SQLException e) {
		
		}
		return null;
	}

	@Override
	public void modifyUser(UserVO user) {
		User entity = new User();
		
		try {			
			entity.setId(user.getId());
			entity.setEmail(user.getEmail());
			entity.setFullName(user.getFullName());
			entity.setLoginName(user.getLoginName());
			entity.setRole(user.getRole());
			
			if(user.getPassword() != null){
				entity.setPassword(user.getPassword());
			} else {
				UserVO u = UserDAOFactory.getUserDao().findById(user.getId());
				entity.setPassword(u.getPassword());
			}		
		
			UserDAOFactory.getUserDao().update(entity);
		} catch (SQLException e) {
		
		}
	}

	@Override
	public UserVO validateUser(String loginName, String password) {
		UserVO ret = null;
		
		try {
			ret = UserDAOFactory.getUserDao().validateUser(loginName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret;
	}

	@Override
	public UserVO getUserByLoginName(String loginName) {
		UserVO ret = null;
		
		try {
			ret = UserDAOFactory.getUserDao().findByLonginName(loginName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret;
	}

	@Override
	public List<UserVO> getAllUser() {		
		List<UserVO> ret = null;
		
		try {
			ret = UserDAOFactory.getUserDao().getAllUser();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret;
	}

	@Override
	public UserVO getUserByFullName(String fullName) {
		UserVO user = null;
		for(User u : UserDAOFactory.getUserDao().getAllUser()){
			if(u.getFullName().equals(fullName)){
				user = UserDAOImpl.entityToVO(u);
				break;
			}
		}
		return user;
	
	}

}
