package hu.neuron.java.refactory.service;

import hu.neuron.java.refactory.vo.TicketVO;

public interface TicketService {
	
	Long createTicket(TicketVO ticket);
	
	void modifyTicket(TicketVO ticket);
	
}
