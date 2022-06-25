package com.revature.service;

import java.util.Optional;

import com.revature.dao.UserImpl;
import com.revature.models.User;

public class UserService {
	protected UserImpl udao;
	
	public UserService(UserImpl udao) {
		this.udao = udao;
	}
	
	// what every user will be able to do(login, etc...)
	public User confirmLogin(String username, String password) {
		User attempt = udao.findUserByUsername(username);
		return (attempt.getPassword().equals(password) ? attempt : new User());
	}
	
	public int registerUser(User u) {
		return udao.insert(u);
	}
	
	// logout will return to login page
}
