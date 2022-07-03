package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.models.User;
import com.revature.service.EmployeeServices;

/**
 * Servlet implementation class LoginManagers
 */
public class LoginManagers extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeServices empServs = new EmployeeServices();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("managers.html").include(request, response);
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User u = empServs.confirmLogin(username, password);
		
		if (u != null && u.getId() > 0 && u.getRole().getRole().equals("Manager")) {
			HttpSession session = request.getSession();
			
			session.setAttribute("the-man", u);
			
			System.out.println("Session is set for " + u);} 
		else {
			response.sendRedirect("index-err.html");
		}
		out.close();
	}

}
