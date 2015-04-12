package hu.neuron.java.refactory.service;

import hu.neuron.java.refactory.dao.ProjectDAOFactory;
import hu.neuron.java.refactory.dao.TicketDAOFactory;
import hu.neuron.java.refactory.dao.ticket.impl.TicketDAOImpl;
import hu.neuron.java.refactory.datasource.FakeDB;
import hu.neuron.java.refactory.entity.Project;
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
		
		}
		return FakeDB.latestId;
	}

	@Override
	public void modifyTicket(TicketVO ticket) {
		Ticket entity = (Ticket) FakeDB.findById(ticket.getId());
		
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
			TicketDAOFactory.getTicketDao().insert(entity);
		} catch (SQLException e) {
		
		}
	}

	@Override
	public TicketVO getTicketById(Long id) {
		Ticket entity = (Ticket) FakeDB.findById(id);
		TicketVO ret = TicketDAOImpl.entityToVO(entity);		
		return ret;
	}

	@Override
	public List<TicketVO> findAllTicketsByUserId(Long id) {
		ArrayList<TicketVO> ret = new ArrayList<TicketVO>();
		
		List<Project> projects = ProjectDAOFactory.getProjectDao().findAllProjectByWorkerId(id);
		
		for(Project p : projects){
			for(Long tid : p.getTicketIds()){
				ret.add(TicketDAOImpl.entityToVO((Ticket) FakeDB.findById(tid)));
			}
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
