package com.revature.service;

import java.util.Optional;

import com.revature.dao.UserDaoImpl;

import com.revature.models.UserEmp;

public class UserService {
	private UserDaoImpl udao;
	
	public UserService(UserDaoImpl udao) {
		this.udao = udao;
	}
	
	// what every user will be able to do(login, etc...)
	public UserEmp confirmLogin(String username, String password) {
		return  udao.findUserByUsername(username);
	}
	
	public int registerUser(UserEmp u) {
		return udao.insert(u);
	}
}
