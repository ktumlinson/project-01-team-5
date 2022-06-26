package com.revature.service;

import java.util.Optional;

import com.revature.dao.UserImpl;
import com.revature.models.User;

public class UserService {
	protected UserImpl udao;
	
	public UserService(UserImpl udao) {
		this.udao = udao;
	}
	
	// tested and works
	// what every user will be able to do(login, etc...)
	public User confirmLogin(String username, String password) {
		
		User attempt = udao.findUserByUsername(username);
		if(attempt == null) {
			return new User();
		}
		return attempt;
	}
	
	// tested and works
	public User findUserByUsername(String username) {
		return udao.findUserByUsername(username);
	}
	
	// tested and works
	public int registerUser(User u) {
		return udao.insert(u);
	}
	
	// logout will return to login page
}
