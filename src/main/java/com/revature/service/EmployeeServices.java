package com.revature.service;

import java.util.List;
import java.util.stream.Collectors;

import com.revature.dao.ReimbursementImpl;
import com.revature.dao.UserImpl;
import com.revature.models.EReimbursementStatus;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public class EmployeeServices extends UserService{
	private UserImpl udao;
	private ReimbursementImpl rdao;
	
	public EmployeeServices(UserImpl udao, ReimbursementImpl rdao) {
		super(udao);
		this.rdao = rdao;
	}
	
	public EmployeeServices() {
		super(new UserImpl());
		this.rdao = new ReimbursementImpl();
	}
	
	// tested and works
	public int newReimbursementRequest(Reimbursement r) {
		return rdao.insert(r);
	}
	
	// tested and works
	public List<Reimbursement> myPendingRequests(User u){
		return rdao.findAllReimbersements().stream()
				.filter(r-> (r.getEmployee().getId() == u.getId() && r.getStatus().getStatus().equals("pending")))
				.collect(Collectors.toList());
	}
	
	// tested and works
		public List<Reimbursement> allRequests(User u){
			return rdao.findAllReimbersements().stream()
					.filter(r-> (r.getEmployee().getId() == u.getId()))
					.collect(Collectors.toList());
		}
	
	
	public List<Reimbursement> myResolvedRequests(User u){
		return rdao.findAllReimbersements().stream()
				.filter(r-> (r.getEmployee().getId() == u.getId() && !r.getStatus().getStatus().equals("pending")))
				.collect(Collectors.toList());
	}
	
	public String myInfo(User u) {
		// display the users information on a table
		return u.toString();
		
	}
	
	public boolean updateInfo(User u) {
		this.udao = new UserImpl();
		return udao.update(u);
	}
	
	// optional for later make an email when the request is resolved, allow for taking in images in the request
}
