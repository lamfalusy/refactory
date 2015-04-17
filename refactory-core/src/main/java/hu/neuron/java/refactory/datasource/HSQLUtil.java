package hu.neuron.java.refactory.datasource;

import hu.neuron.java.refactory.dao.UserDAOFactory;
import hu.neuron.java.refactory.entity.User;
import hu.neuron.java.refactory.type.RoleType;

import java.sql.Connection;
import java.sql.SQLException;

public class HSQLUtil {
	
	public static String CREATE_USER_SQL = "CREATE TABLE users ("+
			 " id IDENTITY,"+
			 " login_name VARCHAR(45) NOT NULL,"+
			 " full_name VARCHAR(45) NOT NULL,"+
			 " email VARCHAR(45) NULL,"+
			 " password VARCHAR(45) NOT NULL,"+
			 " role VARCHAR(45) NOT NULL,"+
			 " UNIQUE (login_name))";
	
	public static String CREATE_PROJECTS_SQL = "CREATE TABLE projects ("+
			 " id IDENTITY,"+
			 " name VARCHAR(45) NOT NULL,"+
			 " created DATE NOT NULL,"+
			 " description VARCHAR(1023) NULL)";
	
	public static String CREATE_TICKETS_SQL = "CREATE TABLE tickets ("+
			" id IDENTITY,"+
			" project_fk INT NOT NULL,"+
			" title VARCHAR(45) NOT NULL,"+
			" type VARCHAR(45) NULL,"+
			" status VARCHAR(45) NULL,"+
			" priority VARCHAR(45) NULL,"+
			" reporter_fk INT NOT NULL,"+
			" assignee_fk INT NULL,"+
			" created DATE NOT NULL,"+
			" deadline DATE NULL,"+
			" description VARCHAR(1023) NULL,"+
			" CONSTRAINT ticket_user_a"+
			"   FOREIGN KEY (assignee_fk)"+
			"   REFERENCES users (id),"+
			" CONSTRAINT ticket_user_r"+
			"   FOREIGN KEY (reporter_fk)"+
			"   REFERENCES users (id),"+
			" CONSTRAINT ticket_project"+
			"   FOREIGN KEY (project_fk)"+
			"   REFERENCES projects (id))";
			  
			  
	public static String CREATE_COMMENTS_SQL = "CREATE TABLE comments ("+
			" id IDENTITY,"+
			" comment VARCHAR(1023) NOT NULL,"+
			" added DATE NOT NULL,"+
			" user_fk INT NOT NULL,"+
			" ticket_fk INT NOT NULL,"+
			" CONSTRAINT comment_user"+
			"   FOREIGN KEY (user_fk)"+
			"   REFERENCES users (id),"+
			" CONSTRAINT comment_ticket"+
			"   FOREIGN KEY (ticket_fk)"+
			"   REFERENCES tickets (id))";


	public static String CREATE_PROJECTS_USER_SW_SQL = "CREATE TABLE projects_users_SW ("+
			" id IDENTITY,"+
			" user_fk INT NOT NULL,"+
			" project_fk INT NOT NULL,"+
			" CONSTRAINT pusw_project"+
			"   FOREIGN KEY (project_fk)"+
			"   REFERENCES projects (id),"+
			" CONSTRAINT pusw_user"+
			"   FOREIGN KEY (user_fk)"+
			"   REFERENCES users (id))";

	
	private HSQLUtil() {
		
	}
	
	public static void initHSQLDB(Connection conn) throws SQLException{
		System.out.println("-----------asd3------------");
		try {
			conn.createStatement().executeUpdate(HSQLUtil.CREATE_USER_SQL);
			conn.createStatement().executeUpdate(HSQLUtil.CREATE_PROJECTS_SQL);
			conn.createStatement().executeUpdate(HSQLUtil.CREATE_TICKETS_SQL);
			conn.createStatement().executeUpdate(HSQLUtil.CREATE_COMMENTS_SQL);
			conn.createStatement().executeUpdate(HSQLUtil.CREATE_PROJECTS_USER_SW_SQL);
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("De am nincs gaz, elvart exception");
		}
		System.out.println("-----------asd4------------");
		insertInitRows(conn);
	}
	
	private static void insertInitRows(Connection conn){
		System.out.println("-----------asd1------------");
		
		User user1 = new User();
		user1.setEmail("adam@adam.ad");
		user1.setFullName("Horváth Ádám");
		user1.setLoginName("horvatha");
		user1.setPassword("horvatha1");
		user1.setRole(RoleType.USER);
		
		try {
			UserDAOFactory.getUserDao().insert(user1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		User user2 = new User();
		user2.setEmail("csaba@csaba.cs");
		user2.setFullName("Lámfalusi Csaba");
		user2.setLoginName("lamfalusics");
		user2.setPassword("lamfalusics1");
		user2.setRole(RoleType.USER);
		
		try {
			UserDAOFactory.getUserDao().insert(user2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		User user3 = new User();
		user3.setEmail("zsolt@zsolt.zs");
		user3.setFullName("Fehérvári Zsolt");
		user3.setLoginName("fehervarizs");
		user3.setPassword("fehervarizs1");
		user3.setRole(RoleType.USER);
		
		try {
			UserDAOFactory.getUserDao().insert(user3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("-----------asd2------------");
//		Project project = new Project();
//		
//		project.setCreated(new Date());
//		project.setDescription("Refactorálást támogató rendszer.");
//		project.setId(id);
//		project.setManagerIds(new ArrayList<Long>());
//		project.getManagerIds().add(user1.getId());
//		project.setWorkerIds(new ArrayList<Long>());
//		project.getWorkerIds().add(user1.getId());
//		project.getWorkerIds().add(user2.getId());
//		project.setName("Refactory");
//		project.setTicketIds(new ArrayList<Long>());
//		
//		id = Long.valueOf(objects.size() + 1);
//		objects.put(id, Ticket.class);
//		
//		Ticket ticket = new Ticket();
//		ticket.setId(id);
//		ticket.setProjectId(project.getId());
//		ticket.setTitle("Nem jó a felirat");
//		ticket.setType(TicketType.BUG);
//		ticket.setStatus(StatusType.NONE);
//		ticket.setPriority(PriorityType.MINOR);
//		ticket.setReporterId(user1.getId());
//		ticket.setAssignee(user1.getId());
//		ticket.setCreated(new Date());
//		ticket.setDeadline(new Date());
//		ticket.setDescription("A megoldási tervben lévő felirat helyett más jelenik meg");
//		ticket.setComments(new ArrayList<Long>());
//		
//		project.getTicketIds().add(ticket.getId());
//		projects.put(project.getId(), project);
//		
//		id = Long.valueOf(objects.size() + 1);
//		objects.put(id, Comment.class);
//		
//		Comment comment1 = new Comment();
//		comment1.setAdded(new Date(105, 4, 4, 12, 1));
//		comment1.setUser(user1.getId());
//		comment1.setComment("Már kész van.");
//		comment1.setId(id);
//		
//		comments.put(id, comment1);
//		
//		id = Long.valueOf(objects.size() + 1);
//		objects.put(id, Comment.class);
//		
//		Comment comment2 = new Comment();
//		comment2.setAdded(new Date(105, 4, 4, 12, 1));
//		comment2.setUser(user1.getId());
//		comment2.setComment("Már kész van.");
//		comment2.setId(id);
//		
//		comments.put(id, comment1);
//		
//		ticket.getComments().add(comment1.getId());
//		ticket.getComments().add(comment2.getId());
//		
//		tickets.put(ticket.getId(), ticket);
		
	}

}
