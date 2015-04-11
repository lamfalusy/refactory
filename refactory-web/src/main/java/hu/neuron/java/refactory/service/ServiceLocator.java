package hu.neuron.java.refactory.service;



public class ServiceLocator {
	
	private static final UserService userService = new UserServiceImpl();
	private static final TicketService ticketService = new TicketServiceImpl();
	private static final ProjectService projectService = new ProjectServiceImpl();
	private static final CommentService commentService = new CommentServiceImpl();
	
	public static UserService getUserService() {
		return userService;
	}

	public static TicketService getTicketService() {
		return ticketService;
	}

	public static ProjectService getProjectService() {
		return projectService;
	}

	public static CommentService getCommentService() {
		return commentService;
	}

	private ServiceLocator() {
		
	}
	
}
