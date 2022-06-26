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
		case "login":// edit to take to the proper page after registration
			RequestHelper.processManagerLogin(request, response);
			break;
		case "employees": // change to the employee page
			RequestHelper.processEmployees(request, response);
			break;
		case "register": // edit to take to the proper page after registration
			RequestHelper.processRegistration(request, response);
			break;
		case "new-reimbursement":
			// make a new reimbursement
			RequestHelper.processReimbursementRequest(request, response);
			break;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
