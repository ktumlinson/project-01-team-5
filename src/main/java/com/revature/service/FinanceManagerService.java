package com.revature.service;

import com.revature.dao.UserDaoImpl;


public class FinanceManagerService extends UserService{

	public FinanceManagerService(UserDaoImpl udao) {
		super(udao);
	}
	
	// specifics to FM(approve and deny Reimbursements
}