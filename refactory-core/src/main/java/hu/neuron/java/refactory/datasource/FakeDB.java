package hu.neuron.java.refactory.datasource;

import hu.neuron.java.refactory.entity.BaseEntity;
import hu.neuron.java.refactory.entity.Comment;
import hu.neuron.java.refactory.entity.Project;
import hu.neuron.java.refactory.entity.Ticket;
import hu.neuron.java.refactory.entity.User;

import java.util.HashMap;

public class FakeDB {
	
	private FakeDB() {
		
	}
	
	public static Long latestId = null;
	
	public static HashMap<Long,Class> objects = new HashMap<Long, Class>();
	public static HashMap<Long,Ticket> tickets = new HashMap<Long, Ticket>();
	public static HashMap<Long,User> users = new HashMap<Long, User>();
	public static HashMap<Long,Project> projects = new HashMap<Long, Project>();
	public static HashMap<Long,Comment> comments = new HashMap<Long, Comment>();
	
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
