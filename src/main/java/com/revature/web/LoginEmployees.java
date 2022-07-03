package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.models.User;
import com.revature.service.EmployeeServices;

/**
 * Servlet implementation class LoginEmployees
 */
public class LoginEmployees extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeServices empServs = new EmployeeServices();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("employees.html").include(request, response);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username + password);
		User u = empServs.confirmLogin(username, password);
		System.out.println(u);
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

}
