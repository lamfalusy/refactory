package hu.neuron.java.refactory.entity;

import java.util.Date;

public class Comment extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String comment;
	private Date added;
	private Long user;

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

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

}
