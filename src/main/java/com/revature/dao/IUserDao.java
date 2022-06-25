package com.revature.dao;


import java.util.HashMap;
import java.util.List;

import com.revature.models.UserEmp;
import com.revature.models.UserRole2;

public interface IUserDao {
	// CRUD
	
	
	
	// create
	public int insert(UserEmp u);
	
	// read
	public List<UserEmp> findAllUsers();
	public UserEmp findUserById(int id);
	public UserEmp findUserByUsername(String username);
	
	// update
	public boolean update(UserEmp u);
	
	// delete
	public boolean delete(UserEmp u);
}
