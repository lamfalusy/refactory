package hu.neuron.java.refactory.util;

import hu.neuron.java.refactory.service.ServiceLocator;
import hu.neuron.java.refactory.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {
	
	private SessionUtil() {
		
	}
	
	public static UserVO getUserFromSession(HttpServletRequest request){
		UserVO ret = SessionHandler.getUserFromSession(request.getSession());
		if(ret == null){
			String loginName = request.getUserPrincipal().getName();
			UserVO loginUser = ServiceLocator.getUserService().getUserByLoginName(loginName);
			if(loginUser != null){
				SessionHandler.setUserToSession(loginUser, request.getSession());
				return loginUser;
			}
		}
		return ret;
	}
	
}
