package hu.neuron.java.refactory.dao.user;

import java.util.List;

import hu.neuron.java.refactory.dao.DAOBase;
import hu.neuron.java.refactory.entity.User;

public interface UserDAO extends DAOBase<User> {
	
	List<User> getAllUser();
	
}
