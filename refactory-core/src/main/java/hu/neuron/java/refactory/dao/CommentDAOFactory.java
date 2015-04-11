package hu.neuron.java.refactory.dao;

import hu.neuron.java.refactory.dao.comment.CommentDAO;
import hu.neuron.java.refactory.dao.comment.impl.CommentDAOImpl;

public class CommentDAOFactory implements DAOFactory<CommentDAO> {

	private CommentDAOFactory() {
		
	}
	
	public static CommentDAO getCommentDao() {
		return new CommentDAOImpl();
	}
	
}
