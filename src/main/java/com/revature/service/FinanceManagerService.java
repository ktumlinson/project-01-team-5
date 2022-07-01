package com.revature.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.revature.dao.ReimbursementImpl;
import com.revature.dao.UserImpl;
import com.revature.models.ReimbursementStatus;
import com.revature.models.UserRole;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public class FinanceManagerService extends UserService{
	private ReimbursementImpl rdao;


	public FinanceManagerService(UserImpl udao, ReimbursementImpl rdao) {
		super(udao);
		this.rdao = rdao;
	}
	
	public FinanceManagerService() {
		super(new UserImpl());
		this.rdao = new ReimbursementImpl();
		
	}
	
	// specifics to FM(approve and deny Reimbursements
	public List<Reimbursement> allReimbursements(){
		return rdao.findAllReimbersements();
	}
	
	// tested and works
	public List<Reimbursement> allPendingReinbursements(){
		return allReimbursements().stream()				// 1 is hardcoded as pending in db
				.filter(r-> r.getStatus().getId() == 1).collect(Collectors.toList());
	}
	
	// tested and works
	public List<Reimbursement> allResolvedReinbursements(){
		return allReimbursements().stream()
				.filter(r-> (r.getStatus().getId() != 1)).collect(Collectors.toList());
	}
	
	// tested and works
	public List<Reimbursement> allReimbursementsByEmployee(String employeeUsername){
		return allReimbursements().stream()
				.filter(r -> (r.getEmployee().getUsername().equals(employeeUsername))).collect(Collectors.toList());
	}
	
	public List<Reimbursement> allReimbursementsByEmployeeId(int employeeId){
		return allReimbursements().stream()
				.filter(r -> (r.getEmployee().getId()==employeeId)).collect(Collectors.toList());
	}
	
	// tested and works
	public List<User> allEmployees(){
		return udao.findAllUsers().stream()
			//	.filter(u -> (u.getRole().getId() == 1))
				.collect(Collectors.toList());
	}
	
	public Reimbursement findReimbursementById(int id) {
		return rdao.findReimbursementById(id);
	}
	
	// tested and works
	public boolean denyReimbursement(Reimbursement r, User manager) {
		r.setStatus(new ReimbursementStatus(3, "rejected"));
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		r.setTimeResolved(ts);
		r.setManagerId(manager);
		return rdao.update(r);
	}
	
	// tested and works
	public boolean approveReimbursement(Reimbursement r, User manager) {
		r.setStatus(new ReimbursementStatus(2, "approved"));
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		r.setTimeResolved(ts);
		r.setManagerId(manager);
		return rdao.update(r);
	}
	
	
}