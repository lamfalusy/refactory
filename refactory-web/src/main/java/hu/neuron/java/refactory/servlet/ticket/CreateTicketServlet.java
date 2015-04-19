package hu.neuron.java.refactory.servlet.ticket;

import hu.neuron.java.refactory.service.ServiceLocator;
import hu.neuron.java.refactory.util.GsonCreatorUtil;
import hu.neuron.java.refactory.util.SessionHandler;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import hu.neuron.java.refactory.vo.ProjectVO;
import hu.neuron.java.refactory.vo.TicketMinVO;
import hu.neuron.java.refactory.vo.TicketVO;
import hu.neuron.java.refactory.vo.UserVO;

/**
 * Servlet implementation class CreateServlet
 */
// @WebServlet(name = "CreateTicketServlet", urlPatterns = {
// "/secured/CreateTicketServlet" })
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

		Gson gson = GsonCreatorUtil.createGson();

		TicketVO ticket = gson.fromJson(jsonRequest, TicketVO.class);
		ticket.setCreated(new Date());
		ticket.setReporter(SessionHandler.getUserFromSession(request.getSession()));
		
		Long id = ServiceLocator.getTicketService().createTicket(ticket);

		if (id != null) {
			ticket.setId(id);
		}

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
