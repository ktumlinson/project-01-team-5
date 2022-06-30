package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.models.User;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String URI = request.getRequestURI().replace("/employee-servlet-app/", "");
		
		switch(URI) {
		// tested and works
		case "reimbursements":		// manager gets all reimbursements
			RequestHelperManagers.getAllReimbursements(request, response);
			break;
		case "reimbursements?status=open":
			RequestHelperManagers.getAllOpenReimbursements(request, response);
			break;
		case "reimbursements?status=closed":
			RequestHelperManagers.getAllOpenReimbursements(request, response);
			break;	
		// tested and works
		case "employees":			// manager get all emps
			RequestHelperManagers.getAllEmployees(request, response);
			break;	
		}
		// tested and works
		if(URI.matches("employees/\\d+")) {		// Can be used by both managers and employees
			String id = URI.replace("employees/", "");
			RequestHelperEmployees.getEmployeeById(request, response, id);
		}
		else if(URI.matches("employees/openrequests")) {		// User wants to view their open requests
			HttpSession sess = request.getSession();
			User u = (User) sess.getAttribute("the-user");
			
			
			String username = URI.replace("employees/", "");
			RequestHelperManagers.getReimbursementByUsername(request, response, u.getUsername());
		}
		else if(URI.matches("employees/closedrequests")) {		// User wants to view their open requests
			HttpSession sess = request.getSession();
			User u = (User) sess.getAttribute("the-user");
			
			
			String username = URI.replace("employees/", "");
			RequestHelperManagers.getReimbursementByUsername(request, response, u.getUsername());
		}
		else if(URI.matches("employees/*")) {		// User wants to view their info
			String username = URI.replace("employees/", "");
			RequestHelperEmployees.getEmployeeByUsername(request, response, username);
		}
		
		// tested and works
		else if(URI.matches("reimbursements/*")) {
			String username = URI.replace("reimbursements/", "");
			RequestHelperManagers.getReimbursementByUsername(request, response, username);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String URI = request.getRequestURI().replace("/employee-servlet-app/", "");
		
		switch(URI) {
		// create a new reimbursement
		case "emp-login":
			RequestHelperLogin.loginEmployee(request, response);
			break;
		case "manager-login":
			RequestHelperLogin.loginManager(request, response);
			break;	
		case "reimbursements":		// Employee's make new reimb
			// tested and works
			RequestHelperEmployees.createNewReimbursement(request, response);
			break;
		// maybe if we register a new user
//		case "employees":			// manager get all emps
//			System.out.println("Post a new Employee");
//			break;	
		}
		if(URI.matches("employees/update")) { // Employees will update their info
			HttpSession sess = request.getSession();
			User u = (User) sess.getAttribute("the-user");
			
			int id = u.getId();
			RequestHelperEmployees.updateInfoByID(request, response, id);
			
		}
		// tested and works
		else if(URI.matches("employees/\\d+")) { // Employees will edit their info
			
			
			String id = URI.replace("employees/", "");
			int idInt = Integer.parseInt(id);
			RequestHelperEmployees.updateInfoByID(request, response, idInt);
		}
		// tested and works
		else if(URI.matches("reimbursements/\\d+")) { // Manager will edit reimbs
			String id = URI.replace("reimbursements/", "");
			RequestHelperManagers.updateReimbursementById(request, response, id);
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String URI = request.getRequestURI().replace("/employee-servlet-app/", "");

		

		
		
	}
	

}
