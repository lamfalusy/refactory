package hu.neuron.java.refactory.dao.comment.impl;

import hu.neuron.java.refactory.dao.FakeAbstractDAOBase;
import hu.neuron.java.refactory.dao.comment.CommentDAO;
import hu.neuron.java.refactory.dao.user.impl.UserDAOImpl;
import hu.neuron.java.refactory.datasource.FakeDB;
import hu.neuron.java.refactory.entity.Comment;
import hu.neuron.java.refactory.entity.User;
import hu.neuron.java.refactory.vo.CommentVO;

public class CommentDAOImpl extends FakeAbstractDAOBase<Comment> implements CommentDAO {
	
	public static CommentVO entitiyToVO(Comment comment){
		CommentVO ret = new CommentVO();
		
		ret.setAdded(comment.getAdded());
		ret.setComment(comment.getComment());
		ret.setId(comment.getId());
		ret.setUser(UserDAOImpl.entityToVO((User) FakeDB.findById(comment.getUser())));
		
		return ret;
	}
	
}
