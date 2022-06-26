package com.revature.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.revature.dao.ReimbursementImpl;
import com.revature.dao.UserImpl;
import com.revature.models.EReimbursementStatus;
import com.revature.models.EUserRole;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public class FinanceManagerService extends UserService{
	private ReimbursementImpl rdao;

	public FinanceManagerService(UserImpl udao, ReimbursementImpl rdao) {
		super(udao);
		this.rdao = rdao;
	}
	
	// specifics to FM(approve and deny Reimbursements
	public List<Reimbursement> allReimbursements(){
		return rdao.findAllReimbersements();
	}
	
	public List<Reimbursement> allPendingReinbursements(){
		return allReimbursements().stream()				// 1 is hardcoded as pending in db
				.filter(r-> r.getId() == 1).collect(Collectors.toList());
	}
	
	public List<Reimbursement> allResolvedReinbursements(){
		return allReimbursements().stream()
				.filter(r-> (r.getId() != 1)).collect(Collectors.toList());
	}
	
	public List<Reimbursement> allReimbursementsByEmployee(String employeeUsername){
		return allReimbursements().stream()
				.filter(r -> (r.getEmployee().getUsername().equals(employeeUsername))).collect(Collectors.toList());
	}
	
	public List<User> allEmployees(){
		return udao.findAllUsers().stream()
				.filter(u -> (u.getRole().equals("Employee"))).collect(Collectors.toList());
	}
	
	
	public void denyReimbursement(Reimbursement r, User manager) {
		r.setStatus(new EReimbursementStatus(3, "rejected"));
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		r.setTimeResolved(ts);
		r.setManagerId(manager);
		rdao.update(r);
	}
	
	public void approveReimbursement(Reimbursement r, User manager) {
		r.setStatus(new EReimbursementStatus(2, "approved"));
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		r.setTimeResolved(ts);
		r.setManagerId(manager);
		rdao.update(r);
	}
}