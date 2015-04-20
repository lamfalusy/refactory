package hu.neuron.java.refactory.service;

import hu.neuron.java.refactory.dao.ProjectDAOFactory;
import hu.neuron.java.refactory.dao.project.impl.ProjectDAOImpl;
import hu.neuron.java.refactory.vo.ProjectVO;

import java.sql.SQLException;
import java.util.List;

public class ProjectServiceImpl implements ProjectService {

	@Override
	public Long createProject(ProjectVO project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUsersToProject(List<Long> userIds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addManagersToProject(List<Long> managerIds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUsersFromProject(List<Long> userIds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeManagersFromProject(List<Long> managerIds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyProject(ProjectVO project) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProject(Long projectId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ProjectVO getProjectById(Long projectId) {
		try {
			return ProjectDAOImpl.entityToVO(ProjectDAOFactory.getProjectDao().findProjectById(projectId));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
