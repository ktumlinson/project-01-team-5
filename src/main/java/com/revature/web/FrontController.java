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
			
		case "reimbursements":		// manager gets all reimbursements
			RequestHelperManagers.getAllReimbursements(request, response);
			break;
		case "reimbursements?status=open":	// manager gets open reimbursements
			RequestHelperManagers.getAllOpenReimbursements(request, response);
			break;
		case "reimbursements?status=closed":	// manager gets closed reimbursements
			RequestHelperManagers.getAllOpenReimbursements(request, response);
			break;	
		case "employees":			// manager get all emps
			RequestHelperManagers.getAllEmployees(request, response);
			break;	
		}
		// Path really used in our app
		if(URI.matches("employees/\\d+")) {		// Can be used by both managers and employees
			String id = URI.replace("employees/", "");
			RequestHelperEmployees.getEmployeeById(request, response, id);
		}
		// User wants to view their open requests
		else if(URI.matches("employees/openrequests")) {		
			HttpSession sess = request.getSession();
			User u = (User) sess.getAttribute("the-user");
			System.out.println("fetching user session for " + u);
			
			String username = URI.replace("employees/", "");
			RequestHelperManagers.getReimbursementsByUsername(request, response, u.getUsername());
		}
		// User wants to view their closed requests
		else if(URI.matches("employees/closedrequests")) {		// User wants to view their open requests
			HttpSession sess = request.getSession();
			User u = (User) sess.getAttribute("the-user");
			System.out.println("fetching user session for " + u);
			
			String username = URI.replace("employees/", "");
			RequestHelperManagers.getReimbursementsByUsername(request, response, u.getUsername());
		}
		// User wants to view their own info
		else if(URI.matches("employees/info")) {		// User wants to view their info
			String username = URI.replace("employees/", "");
			HttpSession sess = request.getSession();
			User u = (User) sess.getAttribute("the-user");
			System.out.println("fetching user session for " + u);
			RequestHelperEmployees.getEmployeeByUsername(request, response, u.getUsername());
		}
		// manager finds a reimbursement by reimbursement id
		else if(URI.matches("reimbursements/\\d+")) { // ??????
			String id = URI.replace("reimbursements/", "");
			int idNum = Integer.parseInt(id);
			HttpSession sess = request.getSession();
			User u = (User) sess.getAttribute("the-man");
			if(u.getUsername().equals("manager")) {
				RequestHelperManagers.getReimbursementByReimbursementId(request, response, idNum);
			}
		}
		
		// ready to test!
		// manager finds all reimbursements by username. Haven't extracted query string correctly
		else if(URI.matches("reimbursements?username=")) { // ??????
			String username = URI.replace("reimbursements?username=", "");
			HttpSession sess = request.getSession();
			User u = (User) sess.getAttribute("the-man");
			if(u.getUsername().equals("manager")) {
				RequestHelperManagers.getReimbursementsByUsername(request, response, username);
			}
			
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String URI = request.getRequestURI().replace("/employee-servlet-app/", "");
		
		switch(URI) {
		
		case "emp-login":
			RequestHelperLogin.loginEmployee(request, response);
			break;
		case "manager-login":
			RequestHelperLogin.loginManager(request, response);
			break;	
			// Employee create a new reimbursement
		case "reimbursements":		
			
				RequestHelperEmployees.createNewReimbursement(request, response);
				break;
			
		// maybe if we register a new user
//		case "employees":			// manager get all emps
//			System.out.println("Post a new Employee");
//			break;	
		}
		// Employees will update their info
		if(URI.matches("employees/update")) { 
			System.out.println("Hit the employees/update path");
				RequestHelperEmployees.updateInfoByID(request, response);
			
		}
		else if(URI.matches("employees/\\d+")) { // Employees will edit their info
			
			
			String id = URI.replace("employees/", "");
			int idInt = Integer.parseInt(id);
			RequestHelperEmployees.updateInfoByID(request, response);
		}
		 // Manager will edit reimbursements by Id
		else if(URI.matches("reimbursements/\\d+")) {
			String id = URI.replace("reimbursements/", "");
			HttpSession sess = request.getSession();
			User u = (User) sess.getAttribute("the-man");
			if(u.getUsername().equals("manager")) {
				RequestHelperManagers.updateReimbursementById(request, response, id);
			}
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String URI = request.getRequestURI().replace("/employee-servlet-app/", "");

		

		
		
	}
	

}
