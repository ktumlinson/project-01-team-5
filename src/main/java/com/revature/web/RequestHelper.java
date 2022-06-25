package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
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

public class RequestHelper {
	private static EmployeeService eserv = new EmployeeService(new EmployeeDao());
	private static EmployeeServices eservs = new EmployeeServices(new UserImpl(), new ReimbursementImpl());
	private static ObjectMapper om = new ObjectMapper();
	
	public static void processEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// the new version of this will simply take them to the page so not sure how this will work
		
		response.setContentType("text/html");
		List<Employee> employees = eserv.getAll();
		String jsonString = om.writeValueAsString(employees);
		PrintWriter out = response.getWriter();
		out.println(jsonString);
	}
	
	public static int processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
			type = EReimbursementType.LODGING;
			break;
		case "travel":
			type = EReimbursementType.TRAVEL;
			break;
		case "food":
			type = EReimbursementType.FOOD;
			break;
		default:
			type = EReimbursementType.OTHER;
			break;
		}
		
		Reimbursement newReim = new Reimbursement(type, EReimbursementStatus.PENDING, amount, ts, null, description, creator, null);
		return eservs.newReimbursementRequest(newReim);
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
			out.println("<h1>Registration failed. Username already exists</h1>");
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
		// if it is an employee then go to the employees page if it is a manager go to managers page
	}
}
