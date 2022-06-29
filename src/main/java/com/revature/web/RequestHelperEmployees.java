package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementImpl;
import com.revature.dao.UserImpl;
import com.revature.models.EReimbursementStatus;
import com.revature.models.EReimbursementType;
import com.revature.models.EUserRole;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.service.EmployeeServices;
import com.revature.service.FinanceManagerService;

import antlr.StringUtils;

public class RequestHelperEmployees {

	private static EmployeeServices eservs = new EmployeeServices(new UserImpl(), new ReimbursementImpl());
	private static FinanceManagerService mservs = new FinanceManagerService(new UserImpl(), new ReimbursementImpl());
	private static ObjectMapper om = new ObjectMapper();
	
	
	public static void getReimbursementById(HttpServletRequest request, HttpServletResponse response, String id) throws IOException, ServletException{
		HttpSession sess = request.getSession();
		PrintWriter out = response.getWriter();
		int idInt = Integer.parseInt(id);
		response.setContentType("application/json");
		
		String queryString = request.getQueryString();
		Reimbursement r = mservs.findReimbursementById(idInt);
			String jsonString = new ObjectMapper().writeValueAsString(r);
			out.println(jsonString);
		
		
	}
	
	public static void getEmployeeById(HttpServletRequest request, HttpServletResponse response, String id) throws IOException, ServletException{
		HttpSession sess = request.getSession();
		PrintWriter out = response.getWriter();
		int idInt = Integer.parseInt(id);
		response.setContentType("application/json");
		User u = eservs.findUserById(idInt);
		String queryString = request.getQueryString();
		
			String jsonString = new ObjectMapper().writeValueAsString(u);
			out.println(jsonString);
		
	}
	
	public static void createNewReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		HttpSession sess = request.getSession();
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		double amount = Double.parseDouble(request.getParameter("amount"));
		String type = request.getParameter("type");
		String description = request.getParameter("description");
		
		User u = eservs.findUserByUsername(username);
		EUserRole role = EUserRole.roleGenerator("Employee");
		EReimbursementType reimbType = EReimbursementType.generater(type);
		EReimbursementStatus reimbStatus = EReimbursementStatus.generater("pending");
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		Reimbursement r = new Reimbursement(reimbType, reimbStatus, amount, ts, null, description, u, User.managerGenerator());
		eservs.newReimbursementRequest(r);
		response.setContentType("application/json");
		
		String queryString = request.getQueryString();
		
			String jsonString = new ObjectMapper().writeValueAsString(r);
			out.println(jsonString);
		
	}
	
	public static void updateInfoByID(HttpServletRequest request, HttpServletResponse response, String id) throws IOException, ServletException{
		int idInt = Integer.parseInt(id);
		HttpSession sess = request.getSession();
		PrintWriter out = response.getWriter();
		
		//String test = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		//System.out.println(test);
		System.out.println(request.getParameterNames());
		response.setContentType("application/json");
		String firstname = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String queryString = request.getQueryString();
		User u = mservs.findUserById(idInt);
		
		if(!firstname.equals("")) {
			u.setFirstname(firstname);
		}
		if(!lastName.equals("")) {
			u.setLastname(lastName);
		}
		if(!password.equals("")) {
			u.setPassword(password);
		}
		if(!email.equals("")) {
			u.setEmail(email);
		}
		
		eservs.updateInfo(u);
		String jsonString = new ObjectMapper().writeValueAsString(u);
		out.println(jsonString);
		
	}
	
	
}
