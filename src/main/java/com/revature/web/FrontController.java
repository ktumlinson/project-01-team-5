package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String URI = request.getRequestURI().replace("/employee-servlet-app/", "");
		switch(URI) {
//		case "login":// edit to take to the proper page after registration
//			RequestHelper.processManagerLogin(request, response);
//			break;
			case "employee-login": // change to the employee page
				System.out.println("Matched the request\n\n\n");
				RequestHelper.checkForSSO(request, response);
				break;
			case "manager-login": // edit to take to the proper page after registration
				RequestHelper.processManagerLogin(request, response);
				break;
			case "employees-home":
				System.out.println("Made it to Employee's Home!");
				RequestHelper.sendEmployeeHomepage(request, response);
				break;
			case "manager-home":
				RequestHelper.processManagerForward(request, response);
			case "new-reimbursement":
				// make a new reimbursement
				RequestHelper.createNewRequest(request, response);
				break;
			case "employees/view-user-info":
				System.out.println("Got here!");
				RequestHelper.viewUserInfo(request, response);
				break;
			case "employees":
				System.out.println("Somehow matched post request to GET request");
				RequestHelper.sendEmployeeHomepage(request, response);
				break;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//doGet(request, response);
		final String URI = request.getRequestURI().replace("/employee-servlet-app/", "");
		switch(URI) {
			case "employees-home": // handles Post Requests done on employee login screen, redirect to Employee's homepage
				System.out.println("Redirected from Employee login");
				doGet(request, response);
				RequestHelper.processEmployeeLogin(request, response);
				break;
			case "managers": // handles Post Requests done on manager login screen, redirect to Manager's homepage
				System.out.println("\n\nInside Manager's POst path");
				RequestHelper.processManagerLogin(request, response);
				break;
			case "employees":
				System.out.println("Got here thru the login screen, need to check for cookie");
				RequestHelper.processEmployeeLogin(request, response);
				break;
		}
	}

}
