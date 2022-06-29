package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginManagers
 */
public class LoginManagers extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("managers.html").include(request, response);
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		if(password.equals("admin123")) {
			out.print("Welcome, " + name);
			HttpSession session = request.getSession();
			session.setAttribute("name", name);
		} else {
			out.print("Sorry, username or password error");
			request.getRequestDispatcher("manager-login.html").include(request, response);
		}
		out.close();
	}

}
