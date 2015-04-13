package hu.neuron.java.refactory.servlet;

import hu.neuron.java.refactory.service.ServiceLocator;
import hu.neuron.java.refactory.type.RoleType;
import hu.neuron.java.refactory.vo.UserVO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserHandlerServlet
 */
public class UserHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserHandlerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fullName = request.getParameter("fullName");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		RoleType roleType = null;
		if(role.equals("User")){
			roleType = RoleType.USER;
		} else if(role.equals("Admin")){
			roleType = RoleType.ADMIN;
		}
		UserVO user = new UserVO(name, fullName, email, password, roleType);
		ServiceLocator.getUserService().createUser(user);
		System.out.println(user.toString());
		
		
	}

}
