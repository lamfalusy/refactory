package hu.neuron.java.refactory.dao;

import hu.neuron.java.refactory.datasource.FakeDB;
import hu.neuron.java.refactory.entity.BaseEntity;

public abstract class FakeAbstractDAOBase<T extends BaseEntity> implements
		DAOBase<T> {

	@Override
	public void insert(T entity) {
		FakeDB.createObject(entity);
	}

	@Override
	public void delete(Long id) {
		FakeDB.deleteObject(id);
	}

	@Override
	public void update(T entity) {
		FakeDB.modifyObject(entity);
	}
}
