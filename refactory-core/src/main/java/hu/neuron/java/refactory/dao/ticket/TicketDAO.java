package hu.neuron.java.refactory.dao.ticket;

import java.sql.SQLException;
import java.util.List;

import hu.neuron.java.refactory.dao.DAOBase;
import hu.neuron.java.refactory.entity.Ticket;
import hu.neuron.java.refactory.vo.TicketVO;


public interface TicketDAO extends DAOBase<Ticket> {

	List<TicketVO> findAllTickets() throws SQLException;
	
	TicketVO findById(Long id) throws SQLException;
	
	List<TicketVO> findAllAvaibleTicketsByUserId(Long userId) throws SQLException;
}
