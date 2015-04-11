package hu.neuron.java.refactory.servlet;

import hu.neuron.java.refactory.service.DBMock;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.neuron.java.refactory.vo.TicketVO;

/**
 * Servlet implementation class DeleteServlet
 */
//@WebServlet(name="DeleteTicketServlet",urlPatterns={"/secured/DeleteTicketServlet"})
public class DeleteTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTicketServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HashMap<Long, TicketVO> db = DBMock.getDb();
		
		if (request.getParameter("id") != null) {
			Long id = new Long(request.getParameter("id"));
			db.remove(id);
			response.getWriter().write(id.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
