package com.revature.web;

import java.io.IOException;
import java.util.HashMap;

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
	
	protected HashMap<String, String> extractQueryParams(String query) {
		String[] splitted = query.split("=");
		HashMap<String, String> map = new HashMap<>();
		if(splitted.length== 0 || splitted.length % 2 == 1) {
			return new HashMap<>();
		}
		int i = 0;
		while(i < splitted.length / 2) {
			map.put(splitted[i], splitted[i + 1]);
			i+= 2;
		}
		System.out.println(map);
		return map;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String URI = request.getRequestURI().replace("/employee-servlet-app/", "");
		String query = "";
		HashMap<String, String> map = new HashMap<>();
		if(request.getQueryString() != null) {
			query = request.getQueryString();
			map = extractQueryParams(query);
		}
		
		
		switch(URI) {
			
		// used on manager's homepage
		
		case "reimbursements":		// manager gets all reimbursements
			// manager gets all open reimbursements
			if(map.containsKey("status") && map.get("status").equals("open")) {
				RequestHelperManagers.getAllOpenReimbursements(request, response);
			}
			// manager gets all closed reimburseements
			else if(map.containsKey("status")) {
				RequestHelperManagers.getAllClosedReimbursements(request, response);
			}
			// manager searches for reimbursement list by username
			else if(map.containsKey("username")) { // ??????
				HttpSession sess = request.getSession();
				User u = (User) sess.getAttribute("the-man");
				String username = map.get("username");
					
				if(u.getRole().getRole().equals("Manager")) {
					RequestHelperManagers.getReimbursementsByUsername(request, response, username);
				}
				
			}
			else {
			RequestHelperManagers.getAllReimbursements(request, response);
			}
			break;
			// manager get all emps
		case "employees":			
			RequestHelperManagers.getAllEmployees(request, response);
			break;
			// manager or employee logs out
		case "logout":
			RequestHelperLogin.logout(request, response);
			break;
		}
		// Path not really used in our app
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
			RequestHelperEmployees.getReimbursementByUsernameOpen(request, response, username);
		}
		// User wants to view their closed requests
		else if(URI.matches("employees/closedrequests")) {		// User wants to view their open requests
			HttpSession sess = request.getSession();
			User u = (User) sess.getAttribute("the-user");
			System.out.println("fetching user session for " + u);
			
			String username = URI.replace("employees/", "");
			RequestHelperEmployees.getReimbursementByUsernameClosed(request, response, username);
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
			HttpSession sess = request.getSession();
			User u = (User) sess.getAttribute("the-man");
			if(map.containsKey("username") ) {
				String username = map.get("username");
				
				if(u.getUsername().equals("manager")) {
					RequestHelperManagers.getReimbursementsByUsername(request, response, username);
				}
			}
			
			
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String URI = request.getRequestURI().replace("/employee-servlet-app/", "");
		
		switch(URI) {
		// dont use these in app only api
		case "emp-login":
			System.out.println("hello world");
			RequestHelperLogin.loginEmployee(request, response);
			break;
		// don't use these in app only api
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
		// not used in app
		else if(URI.matches("employees/\\d+")) { 
			
			String id = URI.replace("employees/", "");
			int idInt = Integer.parseInt(id);
			RequestHelperEmployees.updateInfoByID(request, response);
		}
		 // Manager will edit reimbursements by Id
		else if(URI.matches("reimbursements/\\d+")) {
			String id = URI.replace("reimbursements/", "");
			
				RequestHelperManagers.updateReimbursementById(request, response, id);
		
		}
	}
	
	

}
