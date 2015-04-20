package hu.neuron.java.refactory.service;

import hu.neuron.java.refactory.dao.CommentDAOFactory;
import hu.neuron.java.refactory.entity.Comment;
import hu.neuron.java.refactory.vo.CommentVO;

import java.sql.SQLException;
import java.util.List;

public class CommentServiceImpl implements CommentService {

	@Override
	public Long addCommentToTicket(CommentVO comment, Long ticketId) {
		Long ret = null;
		
		Comment entity = new Comment();
		
		entity.setAdded(comment.getAdded());
		entity.setComment(comment.getComment());
		entity.setTicketId(ticketId);
		entity.setUserId(comment.getUser().getId());
		
		try {
			CommentDAOFactory.getCommentDao().insert(entity);
		} catch (SQLException e) {

		}
		
		return ret;
	}

	@Override
	public List<CommentVO> getCommentsToTicket(Long ticketId) {
		List<CommentVO> ret = null;

		try {
			ret = CommentDAOFactory.getCommentDao().findCommentsByTicket(ticketId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret;
	}

	@Override
	public CommentVO getCommentFromId(Long commentId) {
		CommentVO ret = null;
		
		try {
			ret = CommentDAOFactory.getCommentDao().findById(commentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret;
	}

	@Override
	public void deleteComment(Long commentId) {
		try {
			CommentDAOFactory.getCommentDao().delete(commentId);
		} catch (SQLException e) {

		}
	}
	
}
