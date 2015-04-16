package hu.neuron.java.refactory.service;

import hu.neuron.java.refactory.dao.UserDAOFactory;
import hu.neuron.java.refactory.dao.user.impl.UserDAOImpl;
import hu.neuron.java.refactory.datasource.DataSourceLocator;
import hu.neuron.java.refactory.datasource.FakeDB;
import hu.neuron.java.refactory.entity.User;
import hu.neuron.java.refactory.vo.UserVO;

import java.sql.SQLException;
import java.util.ArrayList;
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
		return FakeDB.latestId;
	}

	@Override
	public void modifyUser(UserVO user) {
		User entity = (User) FakeDB.findById(user.getId());
		
		entity.setId(user.getId());
		entity.setEmail(user.getEmail());
		entity.setFullName(user.getFullName());
		entity.setLoginName(user.getLoginName());
		entity.setRole(user.getRole());
		
		if(user.getPassword() != null){
			entity.setPassword(user.getPassword());
		}
		
		try {
			UserDAOFactory.getUserDao().update(entity);
		} catch (SQLException e) {
		
		}
	}

	@Override
	public UserVO validateUser(String loginName, String password) {
		UserVO ret = null;
		
		for(User u : UserDAOFactory.getUserDao().getAllUser()){
			if(u.getLoginName().equals(loginName) && u.getPassword().equals(password)){
				ret = UserDAOImpl.entityToVO(u);
				break;
			}
		}
		
		return ret;
	}

	@Override
	public UserVO getUserByLoginName(String loginName) {
		UserVO ret = null;
		
		for(User u : UserDAOFactory.getUserDao().getAllUser()){
			if(u.getLoginName().equals(loginName)){
				ret = UserDAOImpl.entityToVO(u);
				break;
			}
		}
		
		return ret;
	}

	@Override
	public List<UserVO> getAllUser() {
		
		System.out.println("kezdodik");
		try {
			DataSourceLocator.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ArrayList<UserVO> ret = new ArrayList<UserVO>();
		
		for(User u : UserDAOFactory.getUserDao().getAllUser()){
			ret.add(UserDAOImpl.entityToVO(u));
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
