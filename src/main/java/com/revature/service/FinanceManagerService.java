package com.revature.service;

import com.revature.dao.UserImpl;

public class FinanceManagerService extends UserService{

	public FinanceManagerService(UserImpl udao) {
		super(udao);
	}
	
	// specifics to FM(approve and deny Reimbursements
}