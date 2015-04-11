package hu.neuron.java.refactory.dao;

import hu.neuron.java.refactory.entity.BaseEntity;

import java.sql.SQLException;

public interface DAOBase<T extends BaseEntity> {

	void insert(T entity) throws SQLException;

	void delete(Long id) throws SQLException;

	void update(T entity) throws SQLException;

}
