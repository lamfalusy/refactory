package hu.neuron.java.refactory.vo;

import hu.neuron.java.refactory.type.TicketType;

import java.io.Serializable;
import java.util.Date;

public class TicketMinVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String title;
	private TicketType type;
	private Date created;
	
	public TicketMinVO() {}
	
	public TicketMinVO(TicketVO ticket) {
		this.id = ticket.getId();
		this.title = ticket.getTitle();
		this.type = ticket.getType();
		this.created = ticket.getCreated();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public TicketType getType() {
		return type;
	}

	public void setType(TicketType type) {
		this.type = type;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "TicketMinVO [id=" + id + ", title=" + title + ", type=" + type
				+ ", created=" + created + "]";
	}
}
