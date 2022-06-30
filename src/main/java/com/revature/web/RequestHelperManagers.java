package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementImpl;
import com.revature.dao.UserImpl;
import com.revature.models.EReimbursementStatus;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.service.EmployeeServices;
import com.revature.service.FinanceManagerService;

public class RequestHelperManagers {

	private static EmployeeServices eservs = new EmployeeServices(new UserImpl(), new ReimbursementImpl());
	private static FinanceManagerService mservs = new FinanceManagerService(new UserImpl(), new ReimbursementImpl());
	private static ObjectMapper om = new ObjectMapper();
	
	public static void getReimbursementByUsername(HttpServletRequest request, HttpServletResponse response, 
			String username) throws IOException, ServletException{
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		List<Reimbursement> reimbList = mservs.allReimbursementsByEmployee(username);
		String jsonString = new ObjectMapper().writeValueAsString(reimbList);
		PrintWriter out = response.getWriter();
		out.println(jsonString);
	}
	
	public static void getAllReimbursements(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
		List<Reimbursement> allReimb = mservs.allReimbursements();
		String jsonString = new ObjectMapper().writeValueAsString(allReimb);
		PrintWriter out = response.getWriter();
		out.println(jsonString);
	}
	
	public static void getAllOpenReimbursements(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
		List<Reimbursement> allReimb = mservs.allPendingReinbursements();
		String jsonString = new ObjectMapper().writeValueAsString(allReimb);
		PrintWriter out = response.getWriter();
		out.println(jsonString);
	}
	
	public static void getAllClosedReimbursements(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
		List<Reimbursement> allReimb = mservs.allResolvedReinbursements();
		String jsonString = new ObjectMapper().writeValueAsString(allReimb);
		PrintWriter out = response.getWriter();
		out.println(jsonString);
	}
	
	public static void getAllEmployees(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
		List<User> allReimb = mservs.allEmployees();
		String jsonString = new ObjectMapper().writeValueAsString(allReimb);
		PrintWriter out = response.getWriter();
		out.println(jsonString);
	}
	
	// Need a way to track manager updates. Can't do it currently
	public static void updateReimbursementById(HttpServletRequest request, HttpServletResponse response, 
			String id) throws IOException, ServletException{
			response.setContentType("application/json");
			response.addHeader("Access-Control-Allow-Origin", "*");
			int idInt = Integer.parseInt(id);
			// V This will be useful just not yet!!
//			mservs.allReimbursementsById(Integer.parseInt(id));
			PrintWriter out = response.getWriter();

			String statusParam = request.getParameter("status");

			Reimbursement r = mservs.findReimbursementById(idInt);
			EReimbursementStatus status = null;
			switch(statusParam) {
			case "2":
				status = EReimbursementStatus.generater("approved");
				break;
			case "3":
				status = EReimbursementStatus.generater("rejected");
				break;
				default:
					status = EReimbursementStatus.generater("pending");
					break;
			}
			r.setStatus(status);
			User m = User.managerGenerator();
			if(status.getId() == 2) {
				mservs.approveReimbursement(r, m);
			}
			else if(status.getId() == 3) {
				mservs.denyReimbursement(r, m);
			}
			String jsonString = new ObjectMapper().writeValueAsString(r);
			out.println(jsonString);
			
	
	}
	
	
}
