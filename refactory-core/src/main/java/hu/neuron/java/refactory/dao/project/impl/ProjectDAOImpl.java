package hu.neuron.java.refactory.dao.project.impl;

import hu.neuron.java.refactory.dao.FakeAbstractDAOBase;
import hu.neuron.java.refactory.dao.project.ProjectDAO;
import hu.neuron.java.refactory.dao.user.impl.UserDAOImpl;
import hu.neuron.java.refactory.datasource.FakeDB;
import hu.neuron.java.refactory.entity.Project;
import hu.neuron.java.refactory.entity.Ticket;
import hu.neuron.java.refactory.entity.User;
import hu.neuron.java.refactory.vo.ProjectVO;
import hu.neuron.java.refactory.vo.TicketVO;
import hu.neuron.java.refactory.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

public class ProjectDAOImpl extends FakeAbstractDAOBase<Project> implements ProjectDAO{
	
	public static ProjectVO entityToVO(Project entity){
		ProjectVO ret = new ProjectVO();
		
		ret.setCreated(entity.getCreated());
		ret.setDescription(entity.getDescription());
		ret.setId(entity.getId());
		ret.setName(entity.getName());
		
		ArrayList<UserVO> managers = new ArrayList<UserVO>();
		for(Long mid : entity.getManagerIds()){
			managers.add(UserDAOImpl.entityToVO((User) FakeDB.findById(mid)));
		}
		ret.setManagers(managers);
		
		ArrayList<TicketVO> tickets = new ArrayList<TicketVO>();
		for(Long tid : entity.getTicketIds()){
			TicketVO ticket = new TicketVO();
			Ticket tEntity = (Ticket) FakeDB.findById(tid);
			
			ticket.setAssignee(UserDAOImpl.entityToVO((User) FakeDB.findById(tEntity.getAssigneeId())));
			ticket.setCreated(tEntity.getCreated());
			ticket.setDeadline(tEntity.getDeadline());
			ticket.setDescription(tEntity.getDescription());
			ticket.setPriority(tEntity.getPriority());
			ticket.setProjectId(ret.getId());
			ticket.setReporter(UserDAOImpl.entityToVO((User) FakeDB.findById(tEntity.getReporterId())));
			ticket.setStatus(tEntity.getStatus());
			ticket.setTitle(tEntity.getTitle());
			ticket.setType(tEntity.getType());
			
			tickets.add(ticket);
		}
		ret.setTickets(tickets);

		ArrayList<UserVO> workers = new ArrayList<UserVO>();
		for(Long wid : entity.getWorkerIds()){
			workers.add(UserDAOImpl.entityToVO((User) FakeDB.findById(wid)));
		}
		ret.setWorkers(workers);
		
		return ret;
	}
	
	public static Project voToEntity(ProjectVO vo){
		Project ret = new Project();
		
		ret.setCreated(vo.getCreated());
		ret.setDescription(vo.getDescription());
		ret.setId(vo.getId());
		ret.setName(vo.getName());
		
		ArrayList<Long> managerIds = new ArrayList<Long>();
		for(UserVO manager : vo.getManagers()){
			managerIds.add(manager.getId());
		}
		ret.setManagerIds(managerIds);
		
		ArrayList<Long> ticketIds = new ArrayList<Long>();
		for(TicketVO ticket : vo.getTickets()){
			ticketIds.add(ticket.getId());
		}
		ret.setTicketIds(ticketIds);

		ArrayList<Long> workerIds = new ArrayList<Long>();
		for(UserVO worker : vo.getWorkers()){
			workerIds.add(worker.getId());
		}
		ret.setWorkerIds(workerIds);
		
		return ret;
	}

	@Override
	public List<Project> findAllProject() {
		ArrayList<Project> ret = new ArrayList<Project>(FakeDB.projects.values());
		return ret;
	}

	@Override
	public List<Project> findAllProjectByWorkerId(Long id) {
		ArrayList<Project> ret = new ArrayList<Project>();
		
		for(Project p : FakeDB.projects.values()){
			if(p.getWorkerIds().contains(id)){
				ret.add(p);
			}
		}
		
		return ret;
	}

	@Override
	public Project findProjectById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
