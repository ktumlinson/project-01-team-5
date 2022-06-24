package com.revature.service;

import java.util.Optional;

import com.revature.dao.UserImpl;
import com.revature.models.User;

public class UserService {
	private UserImpl udao = new UserImpl();
	
	public UserService(UserImpl udao) {
		this.udao = udao;
	}
	
	// what every user will be able to do(login, etc...)
	public User confirmLogin(String username, String password) {
		return  udao.findUserByUsername(username);
	}
	
	public int registerUser(User u) {
		return udao.insert(u);
	}
}
