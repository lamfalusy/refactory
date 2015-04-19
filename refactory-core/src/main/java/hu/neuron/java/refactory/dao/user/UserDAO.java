package hu.neuron.java.refactory.dao.user;

import java.sql.SQLException;
import java.util.List;

import hu.neuron.java.refactory.dao.DAOBase;
import hu.neuron.java.refactory.entity.User;
import hu.neuron.java.refactory.vo.UserVO;

public interface UserDAO extends DAOBase<User> {
	
	List<UserVO> getAllUser() throws SQLException;

	UserVO findById(Long id) throws SQLException;

	UserVO findByLoginName(String loginName) throws SQLException;
	
	UserVO findByName(String loginName) throws SQLException;
	
	UserVO validateUser(String loginName, String password) throws SQLException;
	
}
