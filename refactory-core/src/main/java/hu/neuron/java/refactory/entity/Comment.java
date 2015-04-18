package hu.neuron.java.refactory.entity;

import java.util.Date;

public class Comment extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String comment;
	private Date added;
	private Long userId;
	private Long ticketId;

	public Comment() {
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

}
