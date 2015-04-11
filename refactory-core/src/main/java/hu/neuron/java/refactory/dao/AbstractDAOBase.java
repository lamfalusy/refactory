package hu.neuron.java.refactory.dao;



import hu.neuron.java.refactory.datasource.DataSourceLocator;
import hu.neuron.java.refactory.entity.BaseEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractDAOBase<T extends BaseEntity> implements
		DAOBase<T> {

	@Override
	public void insert(T entity) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DataSourceLocator.getConnection();
			preparedStatement = getInsertStatement(connection, entity);
			preparedStatement.executeUpdate();
		} finally {
			try {
				preparedStatement.close();
			} catch (Throwable t) {

			}
			try {
				connection.close();
			} catch (Throwable t) {

			}
		}
	}

	@Override
	public void delete(Long id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DataSourceLocator.getConnection();
			preparedStatement = getDeleteStatement(connection, id);
			preparedStatement.executeUpdate();
		} finally {
			try {
				preparedStatement.close();
			} catch (Throwable t) {

			}
			try {
				connection.close();
			} catch (Throwable t) {

			}
		}
	}

	@Override
	public void update(T entity) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DataSourceLocator.getConnection();
			preparedStatement = getUpdateStatement(connection, entity);
			preparedStatement.executeUpdate();
		} finally {
			try {
				preparedStatement.close();
			} catch (Throwable t) {

			}
			try {
				connection.close();
			} catch (Throwable t) {

			}
		}
	}

	protected abstract PreparedStatement getInsertStatement(
			Connection connection, T entity) throws SQLException;

	protected abstract PreparedStatement getDeleteStatement(
			Connection connection, Long id) throws SQLException;

	protected abstract PreparedStatement getUpdateStatement(
			Connection connection, T entity) throws SQLException;

}
