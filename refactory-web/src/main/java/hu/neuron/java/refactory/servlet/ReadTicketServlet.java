package hu.neuron.java.refactory.servlet;

import hu.neuron.java.refactory.service.DBMock;
import hu.neuron.java.refactory.util.GsonCreatorUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import hu.neuron.java.refactory.vo.TicketVO;

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

		HashMap<Long, TicketVO> db = DBMock.getDb();
		Gson gson = GsonCreatorUtil.createGson();
		
		if (request.getParameter("id") != null) {

			TicketVO ticket = db.get(new Long(request.getParameter("id")));
			
			response.setCharacterEncoding("UTF-8");

			if (ticket.getComments() != null && !ticket.getComments().isEmpty()) {
				Collections.sort(ticket.getComments(),
						Collections.reverseOrder());
			}

			gson.toJson(ticket, response.getWriter());
		} else {

			Response rv = new Response(new ArrayList<TicketVO>(db.values()));
			response.setCharacterEncoding("UTF-8");
			gson.toJson(rv, response.getWriter());
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

	public static class Response {
		private ArrayList<TicketVO> data;

		public Response(ArrayList<TicketVO> data) {
			super();
			this.data = data;
		}

		public ArrayList<TicketVO> getData() {
			return data;
		}

		public void setData(ArrayList<TicketVO> data) {
			this.data = data;
		}
	}

}
