package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

public class RequestHelper {
	private static EmployeeService eserv = new EmployeeService(new EmployeeDao());
	private static ObjectMapper om = new ObjectMapper();
	
	public static void processEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		List<Employee> employees = eserv.getAll();
		String jsonString = om.writeValueAsString(employees);
		PrintWriter out = response.getWriter();
		out.println(jsonString);
	}
	
	public static void processRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// extract all values
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// construct employee object
		Employee newEmp = new Employee(firstName, lastName, username, password);
		
		// call register method from Service layer
		int pk = eserv.register(newEmp);
		
		// check id if greater than 0 we succesfully created a user
			// using the request forward the request to welcome.html page
		if(pk > 0) {
			// add the user to the session
			newEmp.setId(pk);
			HttpSession sess = request.getSession();
			sess.setAttribute("the-user", newEmp);
			request.getRequestDispatcher("welcome.html").forward(request, response);
		}else {
			
			// todo better logic in service layer to check for psql exceptions
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<h1>Registration faile. Username already exists</h1>");
			out.println("<a href=\"index.html\">Back</a>");
		}
		
	}
	
	
	public static void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Employee e = eserv.confirmLogin(username, password);
		System.out.println(e.getId());
		if(e.getId() > 0) {
			HttpSession sess = request.getSession();
			sess.setAttribute("the-user", e);
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<h1>Welcome " + e.getFistName() + "</h1");
			out.println("<h3>You have successfully logged in!</h3>");
			
			String jsonString = om.writeValueAsString(e);
			out.println(jsonString);
		} else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("No user found, sorry");
		}
		// do employee stuff
	}
}
