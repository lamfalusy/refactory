package hu.neuron.java.refactory.dao.ticket.impl;

import hu.neuron.java.refactory.dao.FakeAbstractDAOBase;
import hu.neuron.java.refactory.dao.project.impl.ProjectDAOImpl;
import hu.neuron.java.refactory.dao.ticket.TicketDAO;
import hu.neuron.java.refactory.dao.user.impl.UserDAOImpl;
import hu.neuron.java.refactory.datasource.FakeDB;
import hu.neuron.java.refactory.entity.Project;
import hu.neuron.java.refactory.entity.Ticket;
import hu.neuron.java.refactory.entity.User;
import hu.neuron.java.refactory.vo.TicketVO;

public class TicketDAOImpl extends FakeAbstractDAOBase<Ticket> implements TicketDAO{

	public static TicketVO entityToVO(Ticket entity){
		TicketVO ret = new TicketVO();
		
		ret.setAssignee(UserDAOImpl.entityToVO((User) FakeDB.findById(entity.getAssigneeId())));
		ret.setCreated(entity.getCreated());
		ret.setDeadline(entity.getDeadline());
		ret.setDescription(entity.getDescription());
		ret.setPriority(entity.getPriority());
		ret.setProjectId(entity.getProjectId());
		ret.setProjectName(ProjectDAOImpl.entityToVO((Project) FakeDB.findById(entity.getProjectId())).getName());
		ret.setReporter(UserDAOImpl.entityToVO((User) FakeDB.findById(entity.getReporterId())));
		ret.setStatus(entity.getStatus());
		ret.setTitle(entity.getTitle());
		ret.setType(entity.getType());
		
		return ret;
	}
	
	public static Ticket voToEntity(TicketVO vo){
		Ticket ret = new Ticket();
		
		ret.setAssignee(vo.getAssignee().getId());
		ret.setCreated(vo.getCreated());
		ret.setDeadline(vo.getDeadline());
		ret.setDescription(vo.getDescription());
		ret.setPriority(vo.getPriority());
		ret.setProjectId(vo.getProjectId());
		ret.setReporterId(vo.getReporter().getId());
		ret.setStatus(vo.getStatus());
		ret.setTitle(vo.getTitle());
		ret.setType(vo.getType());
		
		return ret;
	}
	
}
