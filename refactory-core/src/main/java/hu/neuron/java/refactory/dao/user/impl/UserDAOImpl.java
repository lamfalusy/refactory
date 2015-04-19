package hu.neuron.java.refactory.dao.user.impl;

import hu.neuron.java.refactory.dao.AbstractDAOBase;
import hu.neuron.java.refactory.dao.user.UserDAO;
import hu.neuron.java.refactory.datasource.DataSourceLocator;
import hu.neuron.java.refactory.datasource.SQLUtil;
import hu.neuron.java.refactory.entity.User;
import hu.neuron.java.refactory.type.RoleType;
import hu.neuron.java.refactory.vo.UserVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends AbstractDAOBase<User> implements UserDAO{
	
	private static final String INSERT_SQL = "INSERT INTO users (login_name, full_name, email, password, role) values(?,?,?,?,?);";
	private static final String DELETE_SQL = "DELETE FROM users WHERE id = ?";
	private static final String UPDATE_SQL = "UPDATE users SET login_name = ?, full_name = ?, email = ?, role = ? WHERE id = ?";
	
	public static UserVO entityToVO(User entity){
		if(entity == null) {
			return null;
		}
			
		UserVO ret = new UserVO();
		
		ret.setId(entity.getId());
		ret.setEmail(entity.getEmail());
		ret.setFullName(entity.getFullName());
		ret.setLoginName(entity.getLoginName());
		ret.setRole(entity.getRole());
		
		return ret;
	}
	
	public static User voToEntity(UserVO vo){
		User ret = new User();
		
		ret.setId(vo.getId());
		ret.setEmail(vo.getEmail());
		ret.setFullName(vo.getFullName());
		ret.setLoginName(vo.getLoginName());
		ret.setRole(vo.getRole());
		
		return ret;
	}
	
	private final String FIND_ALL_USER = "SELECT * FROM users;";
	
	@Override
	public List<UserVO> getAllUser() throws SQLException {
		List<UserVO> ret = new ArrayList<UserVO>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DataSourceLocator.getConnection();
			preparedStatement = connection.prepareStatement(FIND_ALL_USER);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				UserVO user = new UserVO();
				user.setId(resultSet.getLong(1));
				user.setLoginName(resultSet.getString(2));
				user.setFullName(resultSet.getString(3));
				user.setEmail(resultSet.getString(4));
				user.setRole(RoleType.valueOf(resultSet.getString(6).toUpperCase()));
				ret.add(user);
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
	
	private final String FIND_BY_ID = "SELECT * FROM users WHERE id = ?;";
	
	@Override
	public UserVO findById(Long id) throws SQLException {
		UserVO ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DataSourceLocator.getConnection();
			preparedStatement = connection.prepareStatement(FIND_BY_ID);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ret = new UserVO();
				ret.setId(resultSet.getLong(1));
				ret.setLoginName(resultSet.getString(2));
				ret.setFullName(resultSet.getString(3));
				ret.setEmail(resultSet.getString(4));
				ret.setRole(RoleType.valueOf(resultSet.getString(6).toUpperCase()));
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
	
	private final String FIND_BY_LOGIN_NAME_ID = "SELECT * FROM users WHERE login_name = ?;";
	
	@Override
	public UserVO findByLoginName(String loginName) throws SQLException {
		UserVO ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DataSourceLocator.getConnection();
			preparedStatement = connection.prepareStatement(FIND_BY_LOGIN_NAME_ID);
			preparedStatement.setString(1, loginName);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ret = new UserVO();
				ret.setId(resultSet.getLong(1));
				ret.setLoginName(resultSet.getString(2));
				ret.setFullName(resultSet.getString(3));
				ret.setEmail(resultSet.getString(4));
				ret.setRole(RoleType.valueOf(resultSet.getString(6).toUpperCase()));
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
	
	private final String VALIDATE_USER = "SELECT * FROM users WHERE login_name = ? AND password = ?;";
	
	@Override
	public UserVO validateUser(String loginName, String password)
			throws SQLException {
		UserVO ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DataSourceLocator.getConnection();
			preparedStatement = connection.prepareStatement(VALIDATE_USER);
			preparedStatement.setString(1, loginName);
			preparedStatement.setString(2, password);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ret = new UserVO();
				ret.setId(resultSet.getLong(1));
				ret.setLoginName(resultSet.getString(2));
				ret.setFullName(resultSet.getString(3));
				ret.setEmail(resultSet.getString(4));
				ret.setRole(RoleType.valueOf(resultSet.getString(6).toUpperCase()));
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
	
private final String FIND_BY_NAME = "SELECT * FROM users WHERE full_name = ?;";
	
	@Override
	public UserVO findByName(String fullName) throws SQLException {
		UserVO ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DataSourceLocator.getConnection();
			preparedStatement = connection.prepareStatement(FIND_BY_NAME);
			preparedStatement.setString(1, fullName);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ret = new UserVO();
				ret.setId(resultSet.getLong(1));
				ret.setLoginName(resultSet.getString(2));
				ret.setFullName(resultSet.getString(3));
				ret.setEmail(resultSet.getString(4));
				ret.setRole(RoleType.valueOf(resultSet.getString(6).toUpperCase()));
			}
		} finally {
			SQLUtil.closeConnection(connection, preparedStatement, resultSet);
		}
		return ret;
	}
	
	@Override
	protected PreparedStatement getInsertStatement(Connection connection,
			User entity) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(INSERT_SQL);
		statement.setString(1, entity.getLoginName());
		statement.setString(2, entity.getFullName());
		statement.setString(3, entity.getEmail());
		statement.setString(4, entity.getPassword());
		statement.setString(5, entity.getRole().getName());
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
			User entity) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);
		statement.setString(1, entity.getLoginName());
		statement.setString(2, entity.getFullName());
		statement.setString(3, entity.getEmail());
		statement.setString(4, entity.getRole().getName());
		statement.setLong(5, entity.getId());
		return statement;
	}
}
