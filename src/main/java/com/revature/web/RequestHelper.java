package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.dao.ReimbursementImpl;
import com.revature.dao.UserImpl;
import com.revature.models.EReimbursementStatus;
import com.revature.models.EReimbursementType;
import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.service.EmployeeService;
import com.revature.service.EmployeeServices;
import com.revature.service.FinanceManagerService;

public class RequestHelper {
	private static EmployeeService eserv = new EmployeeService(new EmployeeDao());
	private static EmployeeServices eservs = new EmployeeServices(new UserImpl(), new ReimbursementImpl());
	private static FinanceManagerService mservs = new FinanceManagerService(new UserImpl(), new ReimbursementImpl());
	private static ObjectMapper om = new ObjectMapper();
	
	/* This is Sophia's App */
//	public static void processEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		// the new version of this will simply take them to the page so not sure how this will work
//		
//		response.setContentType("text/html");
//		List<Employee> employees = eserv.getAll();
//		String jsonString = om.writeValueAsString(employees);
//		PrintWriter out = response.getWriter();
//		out.println(jsonString);
//	}
	
	// This method serves no purpose other than testing right now
	public static void processEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UserImpl udao = new UserImpl();
		response.setContentType("application/json");
		//int insert = udao.insert(new User("arh49", "password", "andy", "h", "andyh@jkd.com", new EUserRole(1, "Employee"), ""));
		PrintWriter out = response.getWriter();
		User u = eservs.findUserByUsername("arh1109");
		
		out.println(u);
		
	}
	
	// This method serves no purpose other than testing right now
	public static void processManagers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UserImpl udao = new UserImpl();
		response.setContentType("application/json");
		//int insert = udao.insert(new User("arh49", "password", "andy", "h", "andyh@jkd.com", new EUserRole(1, "Employee"), ""));
		PrintWriter out = response.getWriter();
		User u = eservs.findUserByUsername("arh1109");
		
		out.println(u);
		
	}
	
	
	/* This is action performed by EmployeeServices */
	public static void processReimbursementRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("username");
		Double amount = Double.parseDouble(request.getParameter("amount"));
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		String description = request.getParameter("description");
		String typeString = request.getParameter("type");
		
		User creator = eservs.findUserByUsername(username);
		EReimbursementType type;
		
		// switch needed based on typeString for now will use a default type
		switch(typeString) {
		case "lodging":
			type = new EReimbursementType(1, "lodging");
			break;
		case "travel":
			type = new EReimbursementType(2, "travel");
			break;
		case "food":
			type = new EReimbursementType(3, "food");
			break;
		default:
			type = new EReimbursementType(4, "other");
			break;
		}
		
		Reimbursement newReim = new Reimbursement(type, new EReimbursementStatus(1, "pending"), amount, ts, null, description, creator, null);
		int pk = eservs.newReimbursementRequest(newReim);
		if(pk > 0) {
			newReim.setId(pk);
			HttpSession ses = request.getSession();
			ses.setAttribute("the-reimbursement", newReim);
			request.getRequestDispatcher("completed-request.html").forward(request, response);
		}
		else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<h1>Sorry ubable to complete request</h1>");
			out.println("<a href=\"index.html\">Back</a>");
		}
	}
	
	/* This is Sophia's App */
	public static void processRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// change to do both employee and manager
		
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
			out.println("<h1>Registration failed. Username already exists</h1>");
			out.println("<a href=\"index.html\">Back</a>");
		}
		
	}
	
	
	
	
	/* This is Sophia's App But we can duplicate into our App */
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
			out.println("<h1>Welcome " + e.getFirstName() + "</h1");
			out.println("<h3>You have successfully logged in!</h3>");
			
			String jsonString = om.writeValueAsString(e);
			out.println(jsonString);
		} else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("No user found, sorry");
		}
		// if it is an employee then go to the employees page if it is a manager go to managers page
	}

	/* This action is performed by EmployeeServices */
	public static void processEmployeeLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		UserImpl udao = new UserImpl();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username+ "\n\n");
		User e = eservs.confirmLogin(username, password);
		
		if(e.getId() > 0) {
			HttpSession sess = request.getSession();
			sess.setAttribute("the-user", e);
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<h1>Welcome " + e.getFirstname() + "</h1");
			out.println("<h3>You have successfully logged in!</h3>");
			
			String jsonString = om.writeValueAsString(e);
			out.println(jsonString);
		} else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("No user found, sorry");
		}
		// if it is an employee then go to the employees page if it is a manager go to managers page
	}
	
	public static void processManagerForward(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		System.out.println(request.getCookies());
		HttpSession sess = request.getSession();
		Cookie[] cookies = request.getCookies();
		String cookie = (String) sess.getAttribute("JSESSIONID");
		PrintWriter out = response.getWriter();
		boolean valid = false;
		for(int i = 0; i < cookies.length; i++) {
			if(cookies[i].getValue().equals(cookie)) {
				valid = true;
				out.println("Successfully sent Cookie!");
			}
		}
		if(!valid) {
			out.println("Logged in but Cookie not sent");
			
		}
		
	}


	/* This action is perfoirmed by ManagerServices */
	public static void processManagerLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		UserImpl udao = new UserImpl();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User e = mservs.confirmLogin(username, password);
		
		if(e.getId() > 0 && e.getRole().getId() == 2) {		// we are authenticated
			HttpSession sess = request.getSession();
			sess.setAttribute("the-man", e);
			PrintWriter out = response.getWriter();
			response.addCookie(new Cookie("JSESSIONID", sess.getId()));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/managers-home");
			dispatcher.forward(request, response);
			System.out.println("\n\n\nForward dididn't work!\n\n");
//			out.println("<h1>Welcome " + e.getFirstname() + "</h1");
//			out.println("<h3>You have successfully logged in!</h3>");
//			
//			String jsonString = om.writeValueAsString(e);
//			out.println(jsonString);
		} else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("No user found, sorry");
		}
		// if it is an employee then go to the employees page if it is a manager go to managers page
	}


	
	

}
