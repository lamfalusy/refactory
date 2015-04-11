package hu.neuron.java.refactory.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ProjectVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Date created;
	private String description;
	private List<UserVO> managers;
	private List<UserVO> workers;
	private List<TicketVO> tickets;
	
	public ProjectVO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<UserVO> getManagers() {
		return managers;
	}

	public void setManagers(List<UserVO> managers) {
		this.managers = managers;
	}

	public List<UserVO> getWorkers() {
		return workers;
	}

	public void setWorkers(List<UserVO> workers) {
		this.workers = workers;
	}

	public List<TicketVO> getTickets() {
		return tickets;
	}

	public void setTickets(List<TicketVO> tickets) {
		this.tickets = tickets;
	}

	@Override
	public String toString() {
		return "ProjectVO [id=" + id + ", name=" + name + ", created="
				+ created + ", description=" + description + ", managers="
				+ managers + ", workers=" + workers + ", tickets=" + tickets
				+ "]";
	}
	
}
