package hu.neuron.java.refactory.filter;

import hu.neuron.java.refactory.type.RoleType;
import hu.neuron.java.refactory.vo.UserVO;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class CommonFilter
 */
//@WebFilter("/CommonFilter")
public class CommonFilter implements Filter {

    /**
     * Default constructor. 
     */	
    public CommonFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		// pass the request along the filter chain
//		//Zsolti ezt megegyszer fe ne commitold!! :D
//		HttpServletRequest httpReq = (HttpServletRequest) request;
//		if(httpReq.getSession().getAttribute("USER_SESSION_KEY")==null) {
//			UserVO user = new UserVO("horvatha", "Horváth Ádám", "h@h.h", "h", RoleType.USER);
//			
//			httpReq.getSession().setAttribute("USER_SESSION_KEY", user);
//		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
