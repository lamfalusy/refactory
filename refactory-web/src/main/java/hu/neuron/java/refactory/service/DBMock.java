package hu.neuron.java.refactory.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import hu.neuron.java.refactory.vo.TicketVO;
import hu.neuron.java.refactory.vo.CommentVO;
import hu.neuron.java.refactory.type.PriorityType;
import hu.neuron.java.refactory.type.StatusType;
import hu.neuron.java.refactory.type.TicketType;


public class DBMock {

	static HashMap<Long, TicketVO> db = new HashMap<Long, TicketVO>();
	
	static {
		long id = (long) db.size() + 1;
		
		TicketVO ticket = new TicketVO();
		ticket.setId(id);
		ticket.setProject("Iflyer-MyUPC");
		ticket.setTitle("Nem jó a felirat");
		ticket.setType(TicketType.BUG);;
		ticket.setStatus(StatusType.NONE);
		ticket.setPriority(PriorityType.MINOR);
		ticket.setReporter("Horváth Ádám");
		ticket.setAssignee("Horváth Ádám");
		ticket.setCreated(new Date());
		ticket.setDeadline(new Date());
		ticket.setDescription("A megoldási tervben lévő felirat helyett más jelenik meg");
		
		List<CommentVO> comments = new ArrayList<CommentVO>();
		
		CommentVO comment = new CommentVO();
		comment.setAdded(new Date(105, 4, 4, 12, 1));
		comment.setUser("Horváth Ádám");
		comment.setComment("Már kész van.");
		
		comments.add(comment);
		
		comment = new CommentVO();
		comment.setUser("Fehérvári Zsolt");
		comment.setAdded(new Date(105, 4, 5, 12, 1));
		comment.setComment("Lófasz van kész.");
		
		comments.add(comment);
		
		ticket.setComments(comments);

		db.put(id, ticket);
	}
	
	public static HashMap<Long, TicketVO> getDb() {
		return db;
	}
}
