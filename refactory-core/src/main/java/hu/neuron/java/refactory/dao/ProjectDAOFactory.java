package hu.neuron.java.refactory.dao;

import hu.neuron.java.refactory.dao.project.ProjectDAO;
import hu.neuron.java.refactory.dao.project.impl.ProjectDAOImpl;

public class ProjectDAOFactory implements DAOFactory<ProjectDAO> {
	
	private ProjectDAOFactory() {
		
	}
	
	public static ProjectDAO getProjectDao() {
		return new ProjectDAOImpl();
	}
	
}
