package hu.neuron.java.refactory.dao.project;

import java.sql.SQLException;
import java.util.List;

import hu.neuron.java.refactory.dao.DAOBase;
import hu.neuron.java.refactory.entity.Project;
import hu.neuron.java.refactory.vo.ProjectVO;

public interface ProjectDAO extends DAOBase<Project> {
	
	public List<ProjectVO> findAllProject() throws SQLException;
	
	public List<ProjectVO> findAllProjectByWorkerId(Long id) throws SQLException;

	ProjectVO findById(Long id) throws SQLException;
	
}
