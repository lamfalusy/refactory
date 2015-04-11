package hu.neuron.java.refactory.service;

import java.util.List;

import hu.neuron.java.refactory.vo.CommentVO;

public interface CommentService {
	
	Long addCommentToTicket(CommentVO comment, Long ticketId);
	
	List<CommentVO> getCommentsToTicket(Long ticketId);
	
	CommentVO getCommentFromId(Long commentId);
	
	void deleteComment(Long commentId);
	
}
