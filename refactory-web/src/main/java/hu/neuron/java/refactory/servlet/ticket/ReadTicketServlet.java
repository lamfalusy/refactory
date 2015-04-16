package hu.neuron.java.refactory.servlet.ticket;

import hu.neuron.java.refactory.service.ServiceLocator;
import hu.neuron.java.refactory.util.GsonCreatorUtil;
import hu.neuron.java.refactory.util.SessionUtil;
import hu.neuron.java.refactory.vo.ResponseWrapper;
import hu.neuron.java.refactory.vo.TicketVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class ReadTicketServlet
 */
//@WebServlet(name = "ReadTicketServlet", urlPatterns = { "/secured/ReadTicketServlet" })
public class ReadTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReadTicketServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Gson gson = GsonCreatorUtil.createGson();

		if (request.getParameter("id") != null) {

			TicketVO ticket = ServiceLocator.getTicketService().getTicketById(Long.valueOf(request.getParameter("id")));
			
			response.setCharacterEncoding("UTF-8");

			if (ticket.getComments() != null && !ticket.getComments().isEmpty()) {
				Collections.sort(ticket.getComments(),
						Collections.reverseOrder());
			}

			gson.toJson(ticket, response.getWriter());
		} else {
			ArrayList<TicketVO> tickets = (ArrayList<TicketVO>) ServiceLocator.getTicketService().findAllTicketsByUserId(SessionUtil.getUserFromSession(request).getId());

			ResponseWrapper<TicketVO> rw = new ResponseWrapper<TicketVO>(tickets);
			response.setCharacterEncoding("UTF-8");

			gson.toJson(rw, response.getWriter());
		}
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
