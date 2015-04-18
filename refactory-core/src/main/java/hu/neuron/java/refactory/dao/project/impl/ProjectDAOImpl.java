package hu.neuron.java.refactory.dao.project.impl;

import hu.neuron.java.refactory.dao.AbstractDAOBase;
import hu.neuron.java.refactory.dao.TicketDAOFactory;
import hu.neuron.java.refactory.dao.UserDAOFactory;
import hu.neuron.java.refactory.dao.project.ProjectDAO;
import hu.neuron.java.refactory.dao.user.impl.UserDAOImpl;
import hu.neuron.java.refactory.datasource.DataSourceLocator;
import hu.neuron.java.refactory.entity.Project;
import hu.neuron.java.refactory.entity.Ticket;
import hu.neuron.java.refactory.entity.User;
import hu.neuron.java.refactory.vo.ProjectVO;
import hu.neuron.java.refactory.vo.TicketVO;
import hu.neuron.java.refactory.vo.UserVO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAOImpl extends AbstractDAOBase<Project> implements ProjectDAO{
	
	private static final String INSERT_SQL = "INSERT INTO projects (name, created, description) values(?,?,?);";
	private static final String DELETE_SQL = "DELETE FROM projects WHERE id = ?";
	private static final String UPDATE_SQL = "UPDATE projects SET name = ?, created = ?, description = ? WHERE id = ?";
	
	public static ProjectVO entityToVO(Project entity) throws SQLException{
		ProjectVO ret = new ProjectVO();
		
		ret.setCreated(entity.getCreated());
		ret.setDescription(entity.getDescription());
		ret.setId(entity.getId());
		ret.setName(entity.getName());
		
		ArrayList<UserVO> managers = new ArrayList<UserVO>();
		for(Long mid : entity.getManagerIds()){
			managers.add(UserDAOFactory.getUserDao().findById(mid));
		}
		ret.setManagers(managers);
		
		ArrayList<TicketVO> tickets = new ArrayList<TicketVO>();
		for(Long tid : entity.getTicketIds()){
			TicketVO ticket = TicketDAOFactory.getTicketDao().findById(tid);
			tickets.add(ticket);
		}
		ret.setTickets(tickets);

		ArrayList<UserVO> workers = new ArrayList<UserVO>();
		for(Long wid : entity.getWorkerIds()){
			workers.add(UserDAOFactory.getUserDao().findById(wid));
		}
		ret.setWorkers(workers);
		
		return ret;
	}
	
	public static Project voToEntity(ProjectVO vo){
		Project ret = new Project();
		
		ret.setCreated(vo.getCreated());
		ret.setDescription(vo.getDescription());
		ret.setId(vo.getId());
		ret.setName(vo.getName());
		
		ArrayList<Long> managerIds = new ArrayList<Long>();
		for(UserVO manager : vo.getManagers()){
			managerIds.add(manager.getId());
		}
		ret.setManagerIds(managerIds);
		
		ArrayList<Long> ticketIds = new ArrayList<Long>();
		for(TicketVO ticket : vo.getTickets()){
			ticketIds.add(ticket.getId());
		}
		ret.setTicketIds(ticketIds);

		ArrayList<Long> workerIds = new ArrayList<Long>();
		for(UserVO worker : vo.getWorkers()){
			workerIds.add(worker.getId());
		}
		ret.setWorkerIds(workerIds);
		
		return ret;
	}
	
	private final String FIND_ALL_PROJECT = "SELECT * FROM projects;";
	
	@Override
	public List<ProjectVO> findAllProject() throws SQLException {
		ArrayList<ProjectVO> ret = new ArrayList<ProjectVO>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DataSourceLocator.getConnection();
			preparedStatement = connection.prepareStatement(FIND_ALL_PROJECT);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ProjectVO project = new ProjectVO();
				project.setId(resultSet.getLong(1));
				project.setName(resultSet.getString(2));
				project.setCreated(resultSet.getDate(3));
				project.setDescription(resultSet.getString(4));
				ret.add(project);
			}
		} finally {
			try {
				resultSet.close();
			} catch (Throwable t) {

			}

			try {
				preparedStatement.close();
			} catch (Throwable t) {

			}
			try {
				connection.close();
			} catch (Throwable t) {

			}
		}
		
		return ret;
	}
	
	private final String FIND_ALL_PROJECT_BY_WORKER_ID = "SELECT p.* FROM projects p, projects_users_SW pusw WHERE p.id = pusw.project_fk and pusw.user_fk = ?;";
	
	@Override
	public List<ProjectVO> findAllProjectByWorkerId(Long id) throws SQLException {
		ArrayList<ProjectVO> ret = new ArrayList<ProjectVO>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DataSourceLocator.getConnection();
			preparedStatement = connection.prepareStatement(FIND_ALL_PROJECT_BY_WORKER_ID);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ProjectVO project = new ProjectVO();
				project.setId(resultSet.getLong(1));
				project.setName(resultSet.getString(2));
				project.setCreated(resultSet.getDate(3));
				project.setDescription(resultSet.getString(4));
				ret.add(project);
			}
		} finally {
			try {
				resultSet.close();
			} catch (Throwable t) {

			}

			try {
				preparedStatement.close();
			} catch (Throwable t) {

			}
			try {
				connection.close();
			} catch (Throwable t) {

			}
		}
		
		return ret;
	}
	
	private final String FIND_BY_ID = "SELECT p.* FROM projects p, projects_users_SW pusw WHERE p.id = pusw.project_fk and pusw.user_fk = ?;";
	
	@Override
	public ProjectVO findById(Long id) throws SQLException {
		ProjectVO ret = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DataSourceLocator.getConnection();
			preparedStatement = connection.prepareStatement(FIND_BY_ID);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ret = new ProjectVO();
				ret.setId(resultSet.getLong(1));
				ret.setName(resultSet.getString(2));
				ret.setCreated(resultSet.getDate(3));
				ret.setDescription(resultSet.getString(4));
			}
		} finally {
			try {
				resultSet.close();
			} catch (Throwable t) {

			}

			try {
				preparedStatement.close();
			} catch (Throwable t) {

			}
			try {
				connection.close();
			} catch (Throwable t) {

			}
		}
		
		return ret;
	}

	@Override
	public Project findProjectById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected PreparedStatement getInsertStatement(Connection connection,
			Project entity) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(INSERT_SQL);
		statement.setString(1, entity.getName());
		statement.setDate(2, new Date(entity.getCreated().getTime()));
		statement.setString(3, entity.getDescription());
		return statement;
	}

	@Override
	protected PreparedStatement getDeleteStatement(Connection connection,
			Long id) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(DELETE_SQL);
		statement.setLong(1, id);
		return statement;
	}

	@Override
	protected PreparedStatement getUpdateStatement(Connection connection,
			Project entity) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);
		statement.setString(1, entity.getName());
		statement.setDate(2, new Date(entity.getCreated().getTime()));
		statement.setString(3, entity.getDescription());
		statement.setLong(4, entity.getId());
		return statement;
	}
	

}
