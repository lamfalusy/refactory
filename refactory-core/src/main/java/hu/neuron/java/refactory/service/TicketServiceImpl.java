package hu.neuron.java.refactory.service;

import hu.neuron.java.refactory.dao.TicketDAOFactory;
import hu.neuron.java.refactory.entity.Ticket;
import hu.neuron.java.refactory.vo.TicketVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketServiceImpl implements TicketService {

	@Override
	public Long createTicket(TicketVO ticket) {
		Ticket entity = new Ticket();
		
		entity.setAssignee(ticket.getAssignee().getId());
		entity.setComments(new ArrayList<Long>());
		entity.setCreated(ticket.getCreated());
		entity.setDeadline(ticket.getDeadline());
		entity.setDescription(ticket.getDescription());
		entity.setPriority(ticket.getPriority());
		entity.setProjectId(ticket.getProjectId());
		entity.setReporterId(ticket.getReporter().getId());
		entity.setStatus(ticket.getStatus());
		entity.setTitle(ticket.getTitle());
		entity.setType(ticket.getType());
		
		try {
			TicketDAOFactory.getTicketDao().insert(entity);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void modifyTicket(TicketVO ticket) {
		Ticket entity = new Ticket();
		
		entity.setAssignee(ticket.getAssignee().getId());
		entity.setCreated(ticket.getCreated());
		entity.setDeadline(ticket.getDeadline());
		entity.setDescription(ticket.getDescription());
		entity.setPriority(ticket.getPriority());
		entity.setProjectId(ticket.getProjectId());
		entity.setReporterId(ticket.getReporter().getId());
		entity.setStatus(ticket.getStatus());
		entity.setTitle(ticket.getTitle());
		entity.setType(ticket.getType());
		
		try {
			TicketDAOFactory.getTicketDao().update(entity);
		} catch (SQLException e) {
		
		}
	}

	@Override
	public TicketVO getTicketById(Long id) {		
		TicketVO ret = null;
		try {
			ret = TicketDAOFactory.getTicketDao().findById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public List<TicketVO> findAllTicketsByUserId(Long id) {
		List<TicketVO> ret = null;
		
		try {
			ret = TicketDAOFactory.getTicketDao().findAllAvaibleTicketsByUserId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret;
	}

	@Override
	public void deleteTicket(Long id) {
		try {
			TicketDAOFactory.getTicketDao().delete(id);
		} catch (SQLException e) {
			
		}
	}

}
