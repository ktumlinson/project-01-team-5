package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementImpl;
import com.revature.dao.UserImpl;
import com.revature.service.EmployeeServices;
import com.revature.service.FinanceManagerService;

public class RequestHelperLogin {

	private static EmployeeServices eservs = new EmployeeServices(new UserImpl(), new ReimbursementImpl());
	private static FinanceManagerService mservs = new FinanceManagerService(new UserImpl(), new ReimbursementImpl());
	private static ObjectMapper om = new ObjectMapper();
	
	public static void loginEmployee(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		response.setContentType("application/json");
		PrintWriter write = response.getWriter();
		write.println("At employee login");
	
	}
	
	public static void loginManager(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		response.setContentType("application/json");
		PrintWriter write = response.getWriter();
		write.println("At manager login");
	
	}
	
}
