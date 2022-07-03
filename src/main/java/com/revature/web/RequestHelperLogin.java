package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementImpl;
import com.revature.dao.UserImpl;
import com.revature.models.User;
import com.revature.service.EmployeeServices;
import com.revature.service.FinanceManagerService;

public class RequestHelperLogin {

	private static EmployeeServices eservs = new EmployeeServices(new UserImpl(), new ReimbursementImpl());
	private static FinanceManagerService mservs = new FinanceManagerService(new UserImpl(), new ReimbursementImpl());
	private static ObjectMapper om = new ObjectMapper();
	
	public static void loginEmployee(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		response.setContentType("application/json");
		EmployeeServices empServs = new EmployeeServices();
		
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		
		User u = empServs.confirmLogin(username, password);
		if (u != null && u.getId() > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("the-user", u);
			
			System.out.println("Session is set for " + u);
		} else {
			// could replace employee-login w/ employee-login-error or something
			response.sendRedirect("employee-login.html");
			out.write("Sorry, username or password error");
		}
		out.close();
	
	}
	
	public static void loginManager(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		response.setContentType("application/json");
		EmployeeServices empServs = new EmployeeServices();
		
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User u = empServs.confirmLogin(username, password);
		if (u != null && u.getId() > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("the-man", u);
			
			System.out.println("Session is set for " + u);
			
		} else {
			// could replace employee-login w/ employee-login-error or something
			response.sendRedirect("manager-login.html");
			out.write("Sorry, username or password error");
		}
		out.close();
	}
	
	public static void logout(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("index.html");
		
		
	}
	
}
