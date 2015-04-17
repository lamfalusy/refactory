package hu.neuron.java.refactory.dao.comment;

import java.sql.SQLException;
import java.util.List;

import hu.neuron.java.refactory.dao.DAOBase;
import hu.neuron.java.refactory.entity.Comment;
import hu.neuron.java.refactory.vo.CommentVO;

public interface CommentDAO extends DAOBase<Comment> {

	CommentVO findById(Long id) throws SQLException;

	List<CommentVO> findCommentsByTicket(Long id) throws SQLException;

}
