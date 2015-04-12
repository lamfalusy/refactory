package hu.neuron.java.refactory.servlet;

import hu.neuron.java.refactory.service.ServiceLocator;
import hu.neuron.java.refactory.sitesimport.exception.SitesImportException;
import hu.neuron.java.refactory.sitesimport.service.SitesImportFacade;
import hu.neuron.java.refactory.sitesimport.service.impl.SitesImportFacadeImpl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.neuron.java.refactory.type.PriorityType;
import hu.neuron.java.refactory.type.StatusType;
import hu.neuron.java.refactory.type.TicketType;
import hu.neuron.java.refactory.vo.TicketVO;

/**
 * Servlet implementation class AddCommentServlet
 */
//@WebServlet(name = "SitesImportServlet", urlPatterns = { "/secured/SitesImportServlet" })
public class SitesImportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SitesImportServlet() {
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
		SitesImportFacade sitesImportFacade = new SitesImportFacadeImpl();
		
		String url = request.getParameter("url");
		
		int index = 0;
		
		try {
			List<TicketVO> tickets = sitesImportFacade.importTicketsFromUrl(url);
			for(TicketVO ticket : tickets){
				index++;
				
				//ticket.setProject("UPC-WS");
				ticket.setTitle("Sites CPD hiba "+index);
				ticket.setType(TicketType.BUG);
				ticket.setStatus(StatusType.NONE);
				ticket.setPriority(PriorityType.MINOR);
				ticket.setReporter(null);
				ticket.setAssignee(null);
				ticket.setCreated(new Date());
				ticket.setDeadline(new Date());
				
				ServiceLocator.getTicketService().createTicket(ticket);
			}
			
		} catch (SitesImportException e) {
			throw new IOException();
		}
	}

}
