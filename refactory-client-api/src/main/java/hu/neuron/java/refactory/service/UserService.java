package hu.neuron.java.refactory.service;

import java.util.List;

import hu.neuron.java.refactory.vo.UserVO;

public interface UserService {
	
	Long createUser(UserVO user);
	
	void modifyUser(UserVO user);
	
	UserVO validateUser(String loginName, String password);
	
	UserVO getUserByLoginName(String loginName);
	
	List<UserVO> getAllUser();
}
