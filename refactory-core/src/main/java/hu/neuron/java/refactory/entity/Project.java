package hu.neuron.java.refactory.entity;

import java.util.Date;
import java.util.List;

public class Project extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private Date created;
	private String description;
	private List<Long> managerIds;
	private List<Long> workerIds;
	private List<Long> ticketIds;
	
	public Project() {
		
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

	public List<Long> getManagerIds() {
		return managerIds;
	}

	public void setManagerIds(List<Long> managerIds) {
		this.managerIds = managerIds;
	}

	public List<Long> getWorkerIds() {
		return workerIds;
	}

	public void setWorkerIds(List<Long> workerIds) {
		this.workerIds = workerIds;
	}

	public List<Long> getTicketIds() {
		return ticketIds;
	}

	public void setTicketIds(List<Long> ticketIds) {
		this.ticketIds = ticketIds;
	}

	@Override
	public String toString() {
		return "Project [id=" + getId() + ", name=" + name + ", created="
				+ created + ", description=" + description + ", managers="
				+ managerIds + ", workers=" + workerIds + ", tickets=" + ticketIds
				+ "]";
	}
}
