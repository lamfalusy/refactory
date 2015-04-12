package hu.neuron.java.refactory.vo;

import hu.neuron.java.refactory.type.PriorityType;
import hu.neuron.java.refactory.type.StatusType;
import hu.neuron.java.refactory.type.TicketType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TicketVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long projectId;
	private String projectName;
	private String title;
	private TicketType type;
	private StatusType status;
	private PriorityType priority;
	private UserVO reporter;
	private UserVO assignee;
	private Date created;
	private Date deadline;
	private String description;
	private List<CommentVO> comments;

	public TicketVO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	public UserVO getReporter() {
		return reporter;
	}

	public void setReporter(UserVO reporter) {
		this.reporter = reporter;
	}

	public UserVO getAssignee() {
		return assignee;
	}

	public void setAssignee(UserVO assignee) {
		this.assignee = assignee;
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

	public List<CommentVO> getComments() {
		return comments;
	}

	public void setComments(List<CommentVO> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "TicketVO [id=" + id + ", project=" + projectId + ", title="
				+ title + ", type=" + type + ", status=" + status
				+ ", priority=" + priority + ", reporter=" + reporter
				+ ", assignee=" + assignee + ", created=" + created
				+ ", deadline=" + deadline + ", description=" + description
				+ "]";
	}
}
