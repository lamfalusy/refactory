package hu.neuron.java.refactory.dao.ticket.impl;

import java.util.ArrayList;
import java.util.List;

import hu.neuron.java.refactory.dao.FakeAbstractDAOBase;
import hu.neuron.java.refactory.dao.comment.impl.CommentDAOImpl;
import hu.neuron.java.refactory.dao.project.impl.ProjectDAOImpl;
import hu.neuron.java.refactory.dao.ticket.TicketDAO;
import hu.neuron.java.refactory.dao.user.impl.UserDAOImpl;
import hu.neuron.java.refactory.datasource.FakeDB;
import hu.neuron.java.refactory.entity.Comment;
import hu.neuron.java.refactory.entity.Project;
import hu.neuron.java.refactory.entity.Ticket;
import hu.neuron.java.refactory.entity.User;
import hu.neuron.java.refactory.vo.CommentVO;
import hu.neuron.java.refactory.vo.TicketVO;

public class TicketDAOImpl extends FakeAbstractDAOBase<Ticket> implements TicketDAO{

	public static TicketVO entityToVO(Ticket entity){
		TicketVO ret = new TicketVO();
		
		ret.setId(entity.getId());
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
		
		if (entity.getComments() != null && !entity.getComments().isEmpty()) {
			List<CommentVO> comments = new ArrayList<CommentVO>();
			for (Long commentId: entity.getComments()) {
				comments.add(CommentDAOImpl.entitiyToVO((Comment) FakeDB.findById(commentId)));
			}
			ret.setComments(comments);  í
		}
		
		
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
