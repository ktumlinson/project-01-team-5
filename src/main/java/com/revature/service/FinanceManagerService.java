package com.revature.service;

import java.sql.Timestamp;
import java.util.List;

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
		return allReimbursements().stream()
				.filter(r-> r.getStatus().equals(EReimbursementStatus.PENDING)).toList();
	}
	
	public List<Reimbursement> allResolvedReinbursements(){
		return allReimbursements().stream()
				.filter(r-> (r.getStatus().equals(EReimbursementStatus.PENDING) == false)).toList();
	}
	
	public List<Reimbursement> allReimbursementsByEmployee(String employeeUsername){
		return allReimbursements().stream()
				.filter(r -> (r.getEmployee().getUsername().equals(employeeUsername))).toList();
	}
	
	public List<User> allEmployees(){
		return udao.findAllUsers().stream()
				.filter(u -> (u.getRole().equals(EUserRole.EMPLOYEE))).toList();
	}
	
	
	public void denyReimbursement(Reimbursement r, User manager) {
		r.setStatus(EReimbursementStatus.REJECTED);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		r.setTimeResolved(ts);
		r.setManagerId(manager);
		rdao.update(r);
	}
	
	public void approveReimbursement(Reimbursement r, User manager) {
		r.setStatus(EReimbursementStatus.APPROVED);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		r.setTimeResolved(ts);
		r.setManagerId(manager);
		rdao.update(r);
	}
}