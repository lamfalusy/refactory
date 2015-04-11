package hu.neuron.java.refactory.service;

import java.util.List;

import hu.neuron.java.refactory.vo.ProjectVO;

public interface ProjectService {
	
	Long createProject(ProjectVO project);
	
	void addUsersToProject(List<Long> userIds);
	
	void addManagersToProject(List<Long> managerIds);
	
	void removeUsersFromProject(List<Long> userIds);
	
	void removeManagersFromProject(List<Long> managerIds);
	
	void modifyProject(ProjectVO project);
	
	void deleteProject(Long projectId);
}
