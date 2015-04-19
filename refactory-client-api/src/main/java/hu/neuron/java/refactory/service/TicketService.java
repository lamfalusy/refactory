package hu.neuron.java.refactory.service;

import java.util.List;

import hu.neuron.java.refactory.vo.TicketVO;

public interface TicketService {
	
	Long createTicket(TicketVO ticket);
	
	void modifyTicket(TicketVO ticket);
	
	TicketVO getTicketById(Long id);
	
	List<TicketVO> findAllTickets();
	
	List<TicketVO> findAllTicketsByUserId(Long id);
	
	void deleteTicket(Long id);
}
