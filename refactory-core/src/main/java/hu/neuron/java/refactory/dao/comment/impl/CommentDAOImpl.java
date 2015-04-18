package hu.neuron.java.refactory.dao.comment.impl;

import hu.neuron.java.refactory.dao.AbstractDAOBase;
import hu.neuron.java.refactory.dao.UserDAOFactory;
import hu.neuron.java.refactory.dao.comment.CommentDAO;
import hu.neuron.java.refactory.datasource.DataSourceLocator;
import hu.neuron.java.refactory.entity.Comment;
import hu.neuron.java.refactory.type.RoleType;
import hu.neuron.java.refactory.vo.CommentVO;
import hu.neuron.java.refactory.vo.UserVO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAOImpl extends AbstractDAOBase<Comment> implements CommentDAO {
	
	private static final String INSERT_SQL = "INSERT INTO comments (comment, added, user_fk, ticket_fk) values( ?,?,?,?);";
	private static final String DELETE_SQL = "DELETE FROM comments WHERE id = ?";
	private static final String UPDATE_SQL = "UPDATE comments SET comment = ?, added = ?, user_fk = ?, ticket_fk = ? WHERE id = ?";
	
	public static CommentVO entitiyToVO(Comment comment) throws SQLException{
		CommentVO ret = new CommentVO();
		
		ret.setAdded(comment.getAdded());
		ret.setComment(comment.getComment());
		ret.setId(comment.getId());
		ret.setUser(UserDAOFactory.getUserDao().findById(comment.getUserId()));
		
		return ret;
	}
	
	private final String FIND_BY_ID = "SELECT * FROM comments WHERE id = ?;";
	
	@Override
	public CommentVO findById(Long id) throws SQLException {
		CommentVO ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DataSourceLocator.getConnection();
			preparedStatement = connection.prepareStatement(FIND_BY_ID);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ret = new CommentVO();
				ret.setId(resultSet.getLong(1));
				ret.setComment(resultSet.getString(2));
				ret.setAdded(resultSet.getDate(3));
				ret.setUser(UserDAOFactory.getUserDao().findById(resultSet.getLong(4)));
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
	
	private final String FIND_COMMENTS_BY_TICKET = "SELECT * FROM comments WHERE ticket_fk = ? ORDER BY added DESC;";
	
	@Override
	public List<CommentVO> findCommentsByTicket(Long id) throws SQLException {
		List<CommentVO> ret = new ArrayList<CommentVO>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DataSourceLocator.getConnection();
			preparedStatement = connection.prepareStatement(FIND_COMMENTS_BY_TICKET);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				CommentVO comment = new CommentVO();
				
				comment.setId(resultSet.getLong(1));
				comment.setComment(resultSet.getString(2));
				comment.setAdded(resultSet.getDate(3));
				comment.setUser(UserDAOFactory.getUserDao().findById(resultSet.getLong(4)));
				
				ret.add(comment);
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
	protected PreparedStatement getInsertStatement(Connection connection,
			Comment entity) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(INSERT_SQL);
		statement.setString(1, entity.getComment());
		statement.setDate(2, new Date(entity.getAdded().getTime()));
		statement.setLong(3, entity.getUserId());
		statement.setLong(4, entity.getTicketId());
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
			Comment entity) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);
		statement.setString(1, entity.getComment());
		statement.setDate(2, new Date(entity.getAdded().getTime()));
		statement.setLong(3, entity.getUserId());
		statement.setLong(4, entity.getTicketId());
		statement.setLong(5, entity.getId());
		return statement;
	}
	
}
