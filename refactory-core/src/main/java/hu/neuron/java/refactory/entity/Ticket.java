package hu.neuron.java.refactory.entity;

import hu.neuron.java.refactory.type.PriorityType;
import hu.neuron.java.refactory.type.StatusType;
import hu.neuron.java.refactory.type.TicketType;

import java.util.Date;
import java.util.List;

public class Ticket extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Long projectId;
	private String title;
	private TicketType type;
	private StatusType status;
	private PriorityType priority;
	private Long reporterId;
	private Long assigneeId;
	private Date created;
	private Date deadline;
	private String description;
	private List<Long> commentIds;

	public Ticket() {
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
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

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

	public PriorityType getPriority() {
		return priority;
	}

	public void setPriority(PriorityType priority) {
		this.priority = priority;
	}

	public Long getReporterId() {
		return reporterId;
	}

	public void setReporterId(Long reporterId) {
		this.reporterId = reporterId;
	}

	public Long getAssigneeId() {
		return assigneeId;
	}

	public void setAssignee(Long assigneeId) {
		this.assigneeId = assigneeId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Long> getComments() {
		return commentIds;
	}

	public void setComments(List<Long> comments) {
		this.commentIds = comments;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + getId() + ", project=" + projectId + ", title="
				+ title + ", type=" + type + ", status=" + status
				+ ", priority=" + priority + ", reporter=" + reporterId
				+ ", assignee=" + assigneeId + ", created=" + created
				+ ", deadline=" + deadline + ", description=" + description
				+ "]";
	}
}
