package hu.neuron.java.refactory.service;

import hu.neuron.java.refactory.dao.TicketDAOFactory;
import hu.neuron.java.refactory.datasource.FakeDB;
import hu.neuron.java.refactory.entity.Ticket;
import hu.neuron.java.refactory.vo.TicketVO;

import java.sql.SQLException;
import java.util.ArrayList;

public class TicketServiceImpl implements TicketService {

	@Override
	public Long createTicket(TicketVO ticket) {
		Ticket entity = new Ticket();
		
		//entity.setAssignee(assigneeId);
		entity.setComments(new ArrayList<Long>());
		entity.setCreated(ticket.getCreated());
		entity.setDeadline(ticket.getDeadline());
		entity.setDescription(ticket.getDescription());
		entity.setPriority(ticket.getPriority());
		//entity.setProjectId(projectId);
		//entity.setReporterId(ticket.getReporter());
		entity.setStatus(ticket.getStatus());
		entity.setTitle(ticket.getTitle());
		entity.setType(ticket.getType());
		
		try {
			TicketDAOFactory.getTicketDao().insert(entity);
		} catch (SQLException e) {
		
		}
		return FakeDB.latestId;
	}

	@Override
	public void modifyTicket(TicketVO ticket) {
		Ticket entity = (Ticket) FakeDB.findById(ticket.getId());
		
		//entity.setAssignee(assigneeId);
		entity.setCreated(ticket.getCreated());
		entity.setDeadline(ticket.getDeadline());
		entity.setDescription(ticket.getDescription());
		entity.setPriority(ticket.getPriority());
		//entity.setProjectId(projectId);
		//entity.setReporterId(ticket.getReporter());
		entity.setStatus(ticket.getStatus());
		entity.setTitle(ticket.getTitle());
		entity.setType(ticket.getType());
		
		try {
			TicketDAOFactory.getTicketDao().insert(entity);
		} catch (SQLException e) {
		
		}
	}

}
