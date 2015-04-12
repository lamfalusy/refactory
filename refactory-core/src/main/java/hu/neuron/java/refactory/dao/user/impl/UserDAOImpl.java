package hu.neuron.java.refactory.dao.user.impl;

import java.util.ArrayList;
import java.util.List;

import hu.neuron.java.refactory.dao.FakeAbstractDAOBase;
import hu.neuron.java.refactory.dao.user.UserDAO;
import hu.neuron.java.refactory.datasource.FakeDB;
import hu.neuron.java.refactory.entity.User;
import hu.neuron.java.refactory.vo.UserVO;

public class UserDAOImpl extends FakeAbstractDAOBase<User> implements UserDAO{
	
	public static UserVO entityToVO(User entity){
		UserVO ret = new UserVO();
		
		ret.setId(entity.getId());
		ret.setEmail(entity.getEmail());
		ret.setFullName(entity.getFullName());
		ret.setLoginName(entity.getLoginName());
		ret.setRole(entity.getRole());
		
		return ret;
	}
	
	public static User voToEntity(UserVO vo){
		User ret = new User();
		
		ret.setId(vo.getId());
		ret.setEmail(vo.getEmail());
		ret.setFullName(vo.getFullName());
		ret.setLoginName(vo.getLoginName());
		ret.setRole(vo.getRole());
		
		return ret;
	}

	@Override
	public List<User> getAllUser() {
		return new ArrayList(FakeDB.users.values());
	}
	
}
