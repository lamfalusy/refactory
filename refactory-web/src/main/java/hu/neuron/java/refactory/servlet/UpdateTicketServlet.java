package hu.neuron.java.refactory.servlet;

import hu.neuron.java.refactory.service.ServiceLocator;
import hu.neuron.java.refactory.util.GsonCreatorUtil;
import hu.neuron.java.refactory.vo.TicketMinVO;
import hu.neuron.java.refactory.vo.TicketVO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

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

		Gson gson = GsonCreatorUtil.createGson();

		TicketVO ticket = gson.fromJson(jsonRequest, TicketVO.class);

		TicketVO ticketOld = ServiceLocator.getTicketService().getTicketById(ticket.getId());

		ticket.setCreated(ticketOld.getCreated());
		ticket.setComments(ticketOld.getComments());

		ServiceLocator.getTicketService().modifyTicket(ticket);

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
