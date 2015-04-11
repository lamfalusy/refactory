package hu.neuron.java.refactory.servlet;

import hu.neuron.java.refactory.service.DBMock;
import hu.neuron.java.refactory.util.GsonCreatorUtil;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import hu.neuron.java.refactory.vo.TicketMinVO;
import hu.neuron.java.refactory.vo.TicketVO;

/**
 * Servlet implementation class UpdateServlet
 */
//@WebServlet(name = "UpdateTicketServlet", urlPatterns = { "/secured/UpdateTicketServlet" })
public class UpdateTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateTicketServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String jsonRequest = request.getParameter("ticket");

		HashMap<Long, TicketVO> db = DBMock.getDb();

		Gson gson = GsonCreatorUtil.createGson();

		TicketVO ticket = gson.fromJson(jsonRequest, TicketVO.class);

		TicketVO ticketOld = db.get(ticket.getId());

		ticket.setCreated(ticketOld.getCreated());
		ticket.setComments(ticketOld.getComments());

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
		// TODO Auto-generated method stub
	}

}
