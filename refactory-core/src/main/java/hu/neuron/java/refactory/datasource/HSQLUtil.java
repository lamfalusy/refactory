package hu.neuron.java.refactory.datasource;

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
			" added VARCHAR(45) NOT NULL,"+
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

		conn.createStatement().executeUpdate(HSQLUtil.CREATE_USER_SQL);
		conn.createStatement().executeUpdate(HSQLUtil.CREATE_PROJECTS_SQL);
		conn.createStatement().executeUpdate(HSQLUtil.CREATE_TICKETS_SQL);
		conn.createStatement().executeUpdate(HSQLUtil.CREATE_COMMENTS_SQL);
		conn.createStatement().executeUpdate(HSQLUtil.CREATE_PROJECTS_USER_SW_SQL);

		
	}
}
