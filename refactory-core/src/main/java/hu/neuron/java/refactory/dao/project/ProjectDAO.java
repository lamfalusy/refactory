package hu.neuron.java.refactory.dao.project;

import java.util.List;

import hu.neuron.java.refactory.dao.DAOBase;
import hu.neuron.java.refactory.entity.Project;

public interface ProjectDAO extends DAOBase<Project> {
	
	public List<Project> findAllProject();
	
	public List<Project> findAllProjectByWorkerId(Long id);
	
	public Project findProjectById(Long id);
}
