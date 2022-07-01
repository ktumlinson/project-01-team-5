package com.revature.dao;

import java.util.List;

import com.revature.models.UserRole;
import com.revature.models.Reimbursement;

public interface IUserRoleDao {
	
	// CRUD
	
		// create
		public int insert(UserRole r);
		
		// read
		public List<UserRole> findAllRoles();
		public UserRole findRoleById(int id);
		
		// update
		public boolean update(UserRole r);
		
		// delete
		public boolean delete(UserRole r);
	
}
