package hu.neuron.java.refactory.service;

import hu.neuron.java.refactory.dao.CommentDAOFactory;
import hu.neuron.java.refactory.datasource.FakeDB;
import hu.neuron.java.refactory.entity.Comment;
import hu.neuron.java.refactory.entity.Ticket;
import hu.neuron.java.refactory.vo.CommentVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentServiceImpl implements CommentService {

	@Override
	public Long addCommentToTicket(CommentVO comment, Long ticketId) {
		Long ret = null;
		
		Comment entity = new Comment();
		
		try {
			CommentDAOFactory.getCommentDao().insert(entity);
			ret = FakeDB.latestId;
			((Ticket) FakeDB.findById(ticketId)).getComments().add(ret);
		} catch (SQLException e) {

		}
		
		return ret;
	}

	@Override
	public List<CommentVO> getCommentsToTicket(Long ticketId) {
		List<CommentVO> ret = new ArrayList<CommentVO>();
		for(Long cid : ((Ticket) FakeDB.findById(ticketId)).getComments()){
			ret.add(this.entitiyToVO((Comment) FakeDB.findById(cid)));
		}
		return ret;
	}

	@Override
	public CommentVO getCommentFromId(Long commentId) {
		return this.entitiyToVO((Comment) FakeDB.findById(commentId));
	}

	@Override
	public void deleteComment(Long commentId) {
		try {
			CommentDAOFactory.getCommentDao().delete(commentId);
		} catch (SQLException e) {

		}
	}
	
	private CommentVO entitiyToVO(Comment comment){
		CommentVO ret = new CommentVO();
		
		ret.setAdded(comment.getAdded());
		ret.setComment(comment.getComment());
		ret.setId(comment.getId());
		//ret.setUser(FakeDB.findById(comment.getUser()));
		
		return ret;
	}
}
