package hu.neuron.java.refactory.servlet;

import hu.neuron.java.refactory.service.DBMock;
import hu.neuron.java.refactory.util.GsonCreatorUtil;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import hu.neuron.java.refactory.vo.TicketMinVO;
import hu.neuron.java.refactory.vo.TicketVO;

/**
 * Servlet implementation class CreateServlet
 */
//@WebServlet(name = "CreateTicketServlet", urlPatterns = { "/secured/CreateTicketServlet" })
public class CreateTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateTicketServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String jsonRequest = request.getParameter("ticket");
		System.out.println(jsonRequest);
		System.out.println("heeelo");
		

		HashMap<Long, TicketVO> db = DBMock.getDb();

		Gson gson = GsonCreatorUtil.createGson();

		TicketVO ticket = gson.fromJson(jsonRequest, TicketVO.class);
		
		ticket.setId(new Long(db.size() + 1));
		ticket.setCreated(new Date());

		db.put(ticket.getId(), ticket);

		response.setCharacterEncoding("UTF-8");
		gson.toJson(new TicketMinVO(ticket), response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
