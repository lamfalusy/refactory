package hu.neuron.java.refactory.service;

import hu.neuron.java.refactory.dao.TicketDAOFactory;
import hu.neuron.java.refactory.dao.UserDAOFactory;
import hu.neuron.java.refactory.entity.Ticket;
import hu.neuron.java.refactory.vo.TicketVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketServiceImpl implements TicketService {

	@Override
	public Long createTicket(TicketVO ticket) {
		try {

			Ticket entity = new Ticket();

			if (ticket.getAssignee().getFullName() != null && !ticket.getAssignee().getFullName().isEmpty()) {
				entity.setAssigneeId(UserDAOFactory.getUserDao()
						.findByName(ticket.getAssignee().getFullName()).getId());
			} else {
				entity.setAssigneeId(ticket.getReporter().getId());
			}
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

			TicketDAOFactory.getTicketDao().insert(entity);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void modifyTicket(TicketVO ticket) {
		Ticket entity = new Ticket();

		entity.setAssigneeId(ticket.getAssignee().getId());
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
			ret = TicketDAOFactory.getTicketDao()
					.findAllAvaibleTicketsByUserId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}

	@Override
	public List<TicketVO> findAllTickets() {
		List<TicketVO> ret = null;

		try {
			ret = TicketDAOFactory.getTicketDao().findAllTickets();
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
