package hu.neuron.java.refactory.dao;

import hu.neuron.java.refactory.dao.user.UserDAO;
import hu.neuron.java.refactory.dao.user.impl.UserDAOImpl;

public class UserDAOFactory implements DAOFactory<UserDAO>{
	
	private UserDAOFactory() {
		
	}
	
	public static UserDAO getUserDao() {
		return new UserDAOImpl();
	}
	
}
