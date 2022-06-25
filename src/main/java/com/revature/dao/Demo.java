package com.revature.dao;

import com.revature.models.UserEmp;
import com.revature.models.UserEmp.UserRole;


public class Demo {

	public static void main(String[] args) {
		UserDaoImpl udao = new UserDaoImpl();
		testingUserDao(udao);
	}
	
	public static void testingUserDao(UserDaoImpl udao) {
		
		UserEmp u = new UserEmp("arh1109", "Darkness", "Andrew", "Hughes", "andyh59", UserRole.EMPLOYEE, 
							"some info");
		
		udao.insert(u);
		
	}
	
}
