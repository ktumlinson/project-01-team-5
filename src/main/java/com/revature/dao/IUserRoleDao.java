package com.revature.dao;

import java.util.List;

import com.revature.models.EUserRole;
import com.revature.models.Reimbursement;

public interface IUserRoleDao {
	
	// CRUD
	
		// create
		public int insert(EUserRole r);
		
		// read
		public List<EUserRole> findAllRoles();
		public EUserRole findRoleById(int id);
		
		// update
		public boolean update(EUserRole r);
		
		// delete
		public boolean delete(EUserRole r);
	
}
