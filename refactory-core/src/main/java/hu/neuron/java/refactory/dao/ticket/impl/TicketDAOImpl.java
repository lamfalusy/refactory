package hu.neuron.java.refactory.dao.ticket.impl;

import hu.neuron.java.refactory.dao.AbstractDAOBase;
import hu.neuron.java.refactory.dao.CommentDAOFactory;
import hu.neuron.java.refactory.dao.ProjectDAOFactory;
import hu.neuron.java.refactory.dao.UserDAOFactory;
import hu.neuron.java.refactory.dao.ticket.TicketDAO;
import hu.neuron.java.refactory.datasource.DataSourceLocator;
import hu.neuron.java.refactory.datasource.SQLUtil;
import hu.neuron.java.refactory.entity.Ticket;
import hu.neuron.java.refactory.type.PriorityType;
import hu.neuron.java.refactory.type.StatusType;
import hu.neuron.java.refactory.type.TicketType;
import hu.neuron.java.refactory.vo.CommentVO;
import hu.neuron.java.refactory.vo.ProjectVO;
import hu.neuron.java.refactory.vo.TicketVO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOImpl extends AbstractDAOBase<Ticket> implements TicketDAO{

	private static final String INSERT_SQL = "INSERT INTO tickets (project_fk, title, type, status, priority, reporter_fk, assignee_fk, created, deadline, description) values(?,?,?,?,?,?,?,?,?,?);";
	private static final String DELETE_SQL = "DELETE FROM tickets WHERE id = ?";
	private static final String UPDATE_SQL = "UPDATE tickets SET project_fk = ?, title = ?, type = ?, status = ?, priority = ?, reporter_fk = ?, assignee_fk = ?, created = ?, deadline = ?, description = ? WHERE id = ?";
	
	public static TicketVO entityToVO(Ticket entity) throws SQLException{
		TicketVO ret = new TicketVO();
		
		ret.setId(entity.getId());
		ret.setAssignee(UserDAOFactory.getUserDao().findById(entity.getAssigneeId()));
		ret.setCreated(entity.getCreated());
		ret.setDeadline(entity.getDeadline());
		ret.setDescription(entity.getDescription());
		ret.setPriority(entity.getPriority());
		ret.setProjectId(entity.getProjectId());
		ret.setProjectName(ProjectDAOFactory.getProjectDao().findById(entity.getProjectId()).getName());
		ret.setReporter(UserDAOFactory.getUserDao().findById(entity.getReporterId()));
		ret.setStatus(entity.getStatus());
		ret.setTitle(entity.getTitle());
		ret.setType(entity.getType());
		
		if (entity.getComments() != null && !entity.getComments().isEmpty()) {
			List<CommentVO> comments = new ArrayList<CommentVO>();
			for (Long commentId: entity.getComments()) {
				comments.add(CommentDAOFactory.getCommentDao().findById(commentId));
			}
			ret.setComments(comments);
		}		
		
		return ret;
	}
	
	public static Ticket voToEntity(TicketVO vo){
		Ticket ret = new Ticket();
		
		ret.setAssigneeId(vo.getAssignee().getId());
		ret.setCreated(vo.getCreated());
		ret.setDeadline(vo.getDeadline());
		ret.setDescription(vo.getDescription());
		ret.setPriority(vo.getPriority());
		ret.setProjectId(vo.getProjectId());
		ret.setReporterId(vo.getReporter().getId());
		ret.setStatus(vo.getStatus());
		ret.setTitle(vo.getTitle());
		ret.setType(vo.getType());
		
		return ret;
	}
	
	private final String FIND_TICKETS = "select * from tickets;";
	
	@Override
	public List<TicketVO> findAllTickets() throws SQLException {
		
		List<TicketVO> ret = new ArrayList<TicketVO>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DataSourceLocator.getConnection();
			preparedStatement = connection.prepareStatement(FIND_TICKETS);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				TicketVO ticket = buildTicket(resultSet);
				ret.add(ticket);
			}
		} finally {
			SQLUtil.closeConnection(connection, preparedStatement, resultSet);
		}
		return ret;
	}
	
	private final String FIND_BY_ID = "SELECT * FROM tickets WHERE id = ?;";
	
	@Override
	public TicketVO findById(Long id) throws SQLException {
		TicketVO ret = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DataSourceLocator.getConnection();
			preparedStatement = connection.prepareStatement(FIND_BY_ID);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ret = buildTicket(resultSet);
			}
		} finally {
			SQLUtil.closeConnection(connection, preparedStatement, resultSet);
		}
		
		return ret;
	}
	
	private final String FIND_ALL_AVAIBLE_TICKET_BY_USER_ID = "SELECT t.* FROM tickets t, projects_users_SW pusw WHERE pusw.user_fk = ? AND t.project_fk = pusw.project_fk;";
	
	@Override
	public List<TicketVO> findAllAvaibleTicketsByUserId(Long userId)
			throws SQLException {
		ArrayList<TicketVO> ret = new ArrayList<TicketVO>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DataSourceLocator.getConnection();
			preparedStatement = connection.prepareStatement(FIND_ALL_AVAIBLE_TICKET_BY_USER_ID);
			preparedStatement.setLong(1, userId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				TicketVO ticket = buildTicket(resultSet);
				ret.add(ticket);
			}
		} finally {
			SQLUtil.closeConnection(connection, preparedStatement, resultSet);
		}
		
		return ret;
	}
	
	private TicketVO buildTicket(ResultSet rs) throws SQLException {
		TicketVO ticket = new TicketVO();
		ticket.setId(rs.getLong(1));
		ProjectVO project = ProjectDAOFactory.getProjectDao().findById(rs.getLong(2));
		ticket.setProjectId(project.getId());
		ticket.setProjectName(project.getName());
		ticket.setTitle(rs.getString(3));
		ticket.setType(TicketType.valueOf(rs.getString(4).toUpperCase()));
		ticket.setStatus(StatusType.valueOf(rs.getString(5).toUpperCase()));
		ticket.setPriority(PriorityType.valueOf(rs.getString(6).toUpperCase()));
		ticket.setReporter(UserDAOFactory.getUserDao().findById(rs.getLong(7)));
		ticket.setAssignee(UserDAOFactory.getUserDao().findById(rs.getLong(8)));
		ticket.setCreated(rs.getDate(9));
		ticket.setDeadline(rs.getDate(10));
		ticket.setDescription(rs.getString(11));
		return ticket;
	}
	
	@Override
	protected PreparedStatement getInsertStatement(Connection connection,
			Ticket entity) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(INSERT_SQL);
		statement.setLong(1, entity.getProjectId());
		statement.setString(2, entity.getTitle());
		statement.setString(3, entity.getType().name());
		statement.setString(4, entity.getStatus().name());
		statement.setString(5, entity.getPriority().name());
		statement.setLong(6, entity.getReporterId());
		statement.setLong(7, entity.getAssigneeId());
		statement.setDate(8, new Date(entity.getCreated().getTime()));
		statement.setDate(9, new Date(entity.getDeadline().getTime()));
		statement.setString(10, entity.getDescription());
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
			Ticket entity) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);
		statement.setLong(1, entity.getProjectId());
		statement.setString(2, entity.getTitle());
		statement.setString(3, entity.getType().name());
		statement.setString(4, entity.getStatus().name());
		statement.setString(5, entity.getPriority().name());
		statement.setLong(6, entity.getReporterId());
		statement.setLong(7, entity.getAssigneeId());
		statement.setDate(8, new Date(entity.getCreated().getTime()));
		statement.setDate(9, new Date(entity.getDeadline().getTime()));
		statement.setString(10, entity.getDescription());
		statement.setLong(11, entity.getId());
		return statement;
	}
}
