package com.revature.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

	public static void getReimbursementsByUsername(HttpServletRequest request, HttpServletResponse response,
			String username) throws IOException, ServletException {
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");

		List<Reimbursement> reimbList = mservs.allReimbursementsByEmployee(username);
		String jsonString = new ObjectMapper().writeValueAsString(reimbList);
		PrintWriter out = response.getWriter();
		out.println(jsonString);
	}

	public static void getReimbursementByReimbursementId(HttpServletRequest request, HttpServletResponse response,
			int id) throws IOException, ServletException {
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");

		Reimbursement reimb = mservs.findReimbursementById(id);
		String jsonString = new ObjectMapper().writeValueAsString(reimb);
		PrintWriter out = response.getWriter();
		out.println(jsonString);
	}

	public static void getAllReimbursements(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
		List<Reimbursement> allReimb = mservs.allReimbursements();
		String jsonString = new ObjectMapper().writeValueAsString(allReimb);
		PrintWriter out = response.getWriter();
		out.println(jsonString);
	}

	public static void getAllOpenReimbursements(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
		List<Reimbursement> allReimb = mservs.allPendingReinbursements();
		String jsonString = new ObjectMapper().writeValueAsString(allReimb);
		PrintWriter out = response.getWriter();
		out.println(jsonString);
	}

	public static void getAllClosedReimbursements(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
		List<Reimbursement> allReimb = mservs.allResolvedReinbursements();
		String jsonString = new ObjectMapper().writeValueAsString(allReimb);
		PrintWriter out = response.getWriter();
		out.println(jsonString);
	}

	public static void getAllEmployees(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
		List<User> allReimb = mservs.allEmployees();
		String jsonString = new ObjectMapper().writeValueAsString(allReimb);
		PrintWriter out = response.getWriter();
		out.println(jsonString);
	}

	// Need a way to track manager updates. Can't do it currently
	public static void updateReimbursementById(HttpServletRequest request, HttpServletResponse response, String id)
			throws IOException, ServletException {
		HttpSession sess = request.getSession();
		User u = (User) sess.getAttribute("the-man");
		if (u.getUsername().equals("manager")) {

			response.setContentType("application/json");
			response.addHeader("Access-Control-Allow-Origin", "*");

			PrintWriter out = response.getWriter();

			// build the GSON object
			Gson gson = new Gson();
			gson = new GsonBuilder().create();
			JsonObject params = new JsonObject();

			try {
				// parse the body of the request to get UserId
				JsonParser jsonParser = new JsonParser();
				JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) request.getInputStream()));
				JsonObject jsonobj = root.getAsJsonObject();

				int idInt = Integer.parseInt(id);

				String statusString = jsonobj.get("status").getAsString();
				Reimbursement r = mservs.findReimbursementById(idInt);

				EReimbursementStatus status = null;
				switch (statusString) {
				case "approved":
					status = EReimbursementStatus.generater("approved");
					mservs.approveReimbursement(r, u);
					break;
				case "rejected":
					status = EReimbursementStatus.generater("rejected");
					mservs.denyReimbursement(r, u);
					break;
				default:
					status = EReimbursementStatus.generater("pending");
					break;
				}

				// write the collection of movies in json to the browser
				if (r != null) {

					String json = gson.toJson(r);
					out.write(json);
				} else {
					// send back a custom error code
					params.addProperty("status", "process failed");
					String json = gson.toJson(params);
					out.write(json);
				}

			} catch (Exception e) {
				// send back a custom error code
				e.printStackTrace();
				params.addProperty("status", "process failed");
				String json = gson.toJson(params);

			}

		}

	}

}
