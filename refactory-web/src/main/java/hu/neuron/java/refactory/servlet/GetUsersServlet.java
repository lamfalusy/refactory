package hu.neuron.java.refactory.servlet;

import hu.neuron.java.refactory.service.ServiceLocator;
import hu.neuron.java.refactory.util.GsonCreatorUtil;
import hu.neuron.java.refactory.vo.ResponseWrapper;
import hu.neuron.java.refactory.vo.UserVO;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetUsersServlet
 */
public class GetUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<UserVO> users = ServiceLocator.getUserService().getAllUser();
		
		ResponseWrapper<UserVO> rw = new ResponseWrapper<UserVO>(users);
		
		response.setCharacterEncoding("UTF-8");

		GsonCreatorUtil.createGson().toJson(rw, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
