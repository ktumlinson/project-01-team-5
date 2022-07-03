package com.revature.service;

import java.util.Optional;

import javax.persistence.NoResultException;

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
		User attempt;
		
		try {
			attempt  = udao.findUserByUsername(username);
			if(attempt.getUsername().equals(username) && attempt.getPassword().equals(password)) {
				return attempt;
			}
		} catch(NoResultException e) {
			return new User();
		}

		return new User();
	}
	
	// tested and works
	public User findUserByUsername(String username) {
		User attempt;
		try {
			attempt  = udao.findUserByUsername(username);
			} catch(NoResultException e) {
				return new User();
			}
		return udao.findUserByUsername(username);
	}
	
	public User findUserById(int id) {
		User attempt;
		try {
			attempt  = udao.findUserById(id);
			} catch(NoResultException e) {
				return new User();
			}
		return new User();
	}
	
	// tested and works
	public int registerUser(User u) {
		return udao.insert(u);
	}
	
	public User returnDefaultManager() {
		return User.managerGenerator();
	}
	
	// logout will return to login page
}
