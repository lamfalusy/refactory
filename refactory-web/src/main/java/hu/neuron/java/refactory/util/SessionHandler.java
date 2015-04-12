package hu.neuron.java.refactory.util;

import hu.neuron.java.refactory.vo.UserVO;

import javax.servlet.http.HttpSession;

public class SessionHandler {
	
	private static final String USER_SESSION_KEY = "USER_SESSION_KEY";

	private SessionHandler() {
		
	}
	
	public static void setUserToSession(UserVO user, HttpSession session){
		session.setAttribute(USER_SESSION_KEY, user);
	}
	
	public static UserVO getUserFromSession(HttpSession session){
		return (UserVO) session.getAttribute(USER_SESSION_KEY);
	}
}
