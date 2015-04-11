package hu.neuron.java.refactory.dao;

import hu.neuron.java.refactory.dao.ticket.TicketDAO;
import hu.neuron.java.refactory.dao.ticket.impl.TicketDAOImpl;

public class TicketDAOFactory implements DAOFactory<TicketDAO> {
	
	private TicketDAOFactory() {
		
	}
	
	public static TicketDAO getTicketDao() {
		return new TicketDAOImpl();
	}
	
}
