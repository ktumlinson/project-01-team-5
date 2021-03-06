package com.revature.dao;

import java.util.List;

import com.revature.models.User;

public interface IUserDao {
	// CRUD
	
	// create
	public int insert(User u);
	
	// read
	public List<User> findAllUsers();
	public User findUserById(int id);
	public User findUserByUsername(String username);
	
	// update
	public boolean update(User u);
	
	// delete
	public boolean delete(User u);
}
