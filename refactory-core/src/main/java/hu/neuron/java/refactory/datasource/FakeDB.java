package hu.neuron.java.refactory.datasource;

import hu.neuron.java.refactory.entity.BaseEntity;
import hu.neuron.java.refactory.entity.Comment;
import hu.neuron.java.refactory.entity.Project;
import hu.neuron.java.refactory.entity.Ticket;
import hu.neuron.java.refactory.entity.User;
import hu.neuron.java.refactory.type.PriorityType;
import hu.neuron.java.refactory.type.RoleType;
import hu.neuron.java.refactory.type.StatusType;
import hu.neuron.java.refactory.type.TicketType;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class FakeDB {
	
	public static Long latestId = null;
	
	public static HashMap<Long,Class> objects = new HashMap<Long, Class>();
	public static HashMap<Long,Ticket> tickets = new HashMap<Long, Ticket>();
	public static HashMap<Long,User> users = new HashMap<Long, User>();
	public static HashMap<Long,Project> projects = new HashMap<Long, Project>();
	public static HashMap<Long,Comment> comments = new HashMap<Long, Comment>();
		
	static {
		long id = Long.valueOf(objects.size() + 1);
		objects.put(id, User.class);
		
		User user1 = new User();
		user1.setEmail("adam@adam.ad");
		user1.setFullName("Horváth Ádám");
		user1.setId(id);
		user1.setLoginName("horvatha");
		user1.setPassword("horvatha1");
		user1.setRole(RoleType.USER);
		
		users.put(id, user1);
		
		id = Long.valueOf(objects.size() + 1);
		objects.put(id, User.class);
		
		User user2 = new User();
		user2.setEmail("csaba@csaba.cs");
		user2.setFullName("Lámfalusi Csaba");
		user2.setId(id);
		user2.setLoginName("lamfalusics");
		user2.setPassword("lamfalusics1");
		user2.setRole(RoleType.USER);
		
		users.put(id, user2);
		
		id = Long.valueOf(objects.size() + 1);
		objects.put(id, Project.class);
		
		Project project = new Project();
		
		project.setCreated(new Date());
		project.setDescription("Refactorálást támogató rendszer.");
		project.setId(id);
		project.setManagerIds(new ArrayList<Long>());
		project.getManagerIds().add(user1.getId());
		project.setWorkerIds(new ArrayList<Long>());
		project.getWorkerIds().add(user1.getId());
		project.getWorkerIds().add(user2.getId());
		project.setName("Refactory");
		project.setTicketIds(new ArrayList<Long>());
		
		id = Long.valueOf(objects.size() + 1);
		objects.put(id, Ticket.class);
		
		Ticket ticket = new Ticket();
		ticket.setId(id);
		ticket.setProjectId(project.getId());
		ticket.setTitle("Nem jó a felirat");
		ticket.setType(TicketType.BUG);
		ticket.setStatus(StatusType.NONE);
		ticket.setPriority(PriorityType.MINOR);
		ticket.setReporterId(user1.getId());
		ticket.setAssignee(user1.getId());
		ticket.setCreated(new Date());
		ticket.setDeadline(new Date());
		ticket.setDescription("A megoldási tervben lévő felirat helyett más jelenik meg");
		ticket.setComments(new ArrayList<Long>());
		
		project.getTicketIds().add(ticket.getId());
		projects.put(project.getId(), project);
		
		id = Long.valueOf(objects.size() + 1);
		objects.put(id, Comment.class);
		
		Comment comment1 = new Comment();
		comment1.setAdded(new Date(105, 4, 4, 12, 1));
		comment1.setUser(user1.getId());
		comment1.setComment("Már kész van.");
		comment1.setId(id);
		
		comments.put(id, comment1);
		
		id = Long.valueOf(objects.size() + 1);
		objects.put(id, Comment.class);
		
		Comment comment2 = new Comment();
		comment2.setAdded(new Date(105, 4, 4, 12, 1));
		comment2.setUser(user1.getId());
		comment2.setComment("Már kész van.");
		comment2.setId(id);
		
		comments.put(id, comment1);
		
		ticket.getComments().add(comment1.getId());
		ticket.getComments().add(comment2.getId());
		
		tickets.put(ticket.getId(), ticket);
		
	}
	
	private FakeDB() {
		
	}
	
	public static Long createObject(BaseEntity baseEntity){
		Long id = Long.valueOf(objects.size() + 1);
		latestId = id;
		
		objects.put(id, baseEntity.getClass());
		
		if(baseEntity instanceof Ticket){
			tickets.put(id, (Ticket) baseEntity);
		} else if(baseEntity instanceof User){
			users.put(id, (User) baseEntity);
		} else if(baseEntity instanceof Project){
			projects.put(id, (Project) baseEntity);
		} else if(baseEntity instanceof Comment){
			comments.put(id, (Comment) baseEntity);
		}
		
		return id;
	}
	
	public static BaseEntity findById(Long id){
		BaseEntity ret = null;
		
		Class c = objects.get(id);
		if(Ticket.class.equals(c)){
			ret = tickets.get(id);
		} else if(User.class.equals(c)){
			ret = users.get(id);
		} else if(Project.class.equals(c)){
			ret = projects.get(id);
		} else if(Comment.class.equals(c)){
			ret = comments.get(id);
		}
		
		return ret;
	}
	
	public static void modifyObject(BaseEntity baseEntity){
		
		if(baseEntity.getId() != null){
			Class c = objects.get(baseEntity.getId());
			if(Ticket.class.equals(c)){
				tickets.put(baseEntity.getId(),(Ticket) baseEntity);
			} else if(User.class.equals(c)){
				users.put(baseEntity.getId(),(User) baseEntity);
			} else if(Project.class.equals(c)){
				projects.put(baseEntity.getId(),(Project) baseEntity);
			} else if(Comment.class.equals(c)){
				comments.put(baseEntity.getId(),(Comment) baseEntity);
			}
		}
		
	}
	
	public static void deleteObject(Long id){
		
		if(id != null){
			Class c = objects.get(id);
			if(Ticket.class.equals(c)){
				tickets.remove(id);
			} else if(User.class.equals(c)){
				users.remove(id);
			} else if(Project.class.equals(c)){
				projects.remove(id);
			} else if(Comment.class.equals(c)){
				comments.remove(id);
			}
			objects.remove(id);
		}
		
	}
}
