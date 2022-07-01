package com.revature.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

	public static void getReimbursementById(HttpServletRequest request, HttpServletResponse response, String id)
			throws IOException, ServletException {
		HttpSession sess = request.getSession();
		PrintWriter out = response.getWriter();
		int idInt = Integer.parseInt(id);
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");

		String queryString = request.getQueryString();
		Reimbursement r = mservs.findReimbursementById(idInt);
		String jsonString = new ObjectMapper().writeValueAsString(r);
		out.println(jsonString);

	}

	public static void getEmployeeById(HttpServletRequest request, HttpServletResponse response, String id)
			throws IOException, ServletException {
		HttpSession sess = request.getSession();
		PrintWriter out = response.getWriter();
		int idInt = Integer.parseInt(id);
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
		User u = eservs.findUserById(idInt);
		String queryString = request.getQueryString();

		String jsonString = new ObjectMapper().writeValueAsString(u);
		out.println(jsonString);

	}

	public static void getEmployeeByUsername(HttpServletRequest request, HttpServletResponse response, String username)
			throws IOException, ServletException {
		HttpSession sess = request.getSession();
		PrintWriter out = response.getWriter();

		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
		User u = eservs.findUserByUsername(username);
		String queryString = request.getQueryString();

		String jsonString = new ObjectMapper().writeValueAsString(u);
		out.println(jsonString);

	}

	public static void createNewReimbursement(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession sess = request.getSession();
		User u = (User) sess.getAttribute("the-user");
		if (u.getId() > 0) {
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

				int userId = u.getId();
				String username = u.getUsername();
				double amount = jsonobj.get("amount").getAsDouble();
				String type = jsonobj.get("type").getAsString();
				String description = jsonobj.get("description").getAsString();

				EReimbursementType reimbType = EReimbursementType.generater(type);
				EReimbursementStatus reimbStatus = EReimbursementStatus.generater("pending");
				Timestamp ts = new Timestamp(System.currentTimeMillis());
				Reimbursement r = new Reimbursement(reimbType, reimbStatus, amount, ts, null, description, u,
						User.managerGenerator());
				int pk = eservs.newReimbursementRequest(r);

				// write the collection of movies in json to the browser
				if (pk > 0) {

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
				out.write(json);
			}
		}

	}

	public static void updateInfoByID(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession sess = request.getSession();
		// Might throw NullPointer Exception V
		User u = (User) sess.getAttribute("the-user");
		System.out.println("fetched user session for " + u);
		if (u.getId() > 0) {
			// they are authenticated, paste all the code below in here
			PrintWriter out = response.getWriter();

			// String test =
			// request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			// System.out.println(test);

			response.setContentType("application/json");
			response.addHeader("Access-Control-Allow-Origin", "*");

			Gson gson = new Gson();
			gson = new GsonBuilder().create();
			JsonObject params = new JsonObject();

			try {
				// parse the body of the request to get UserId
				JsonParser jsonParser = new JsonParser();
				JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) request.getInputStream()));
				JsonObject jsonobj = root.getAsJsonObject();

				u.setFirstname(jsonobj.get("firstname").getAsString());
				u.setPassword(jsonobj.get("password").getAsString());
				u.setLastname(jsonobj.get("password").getAsString());
				u.setEmail(jsonobj.get("email").getAsString());

				// call the movie service's get movies by getMoviesByUserId();
				boolean result = eservs.updateInfo(u);

				// write the collection of movies in json to the browser
				if (result != false) {
					params.addProperty("status", "success");
					String json = gson.toJson(u);
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
				out.write(json);
			}
		}

	}

	public static void updateInfoByUsername(HttpServletRequest request, HttpServletResponse response, String username)
			throws IOException, ServletException {

		HttpSession sess = request.getSession();
		PrintWriter out = response.getWriter();

		// String test =
		// request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		// System.out.println(test);

		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
		String firstname = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String queryString = request.getQueryString();
		User u = mservs.findUserByUsername(username);

		if (!firstname.equals("")) {
			u.setFirstname(firstname);
		}
		if (!lastName.equals("")) {
			u.setLastname(lastName);
		}
		if (!password.equals("")) {
			u.setPassword(password);
		}
		if (!email.equals("")) {
			u.setEmail(email);
		}

		eservs.updateInfo(u);
		String jsonString = new ObjectMapper().writeValueAsString(u);
		out.println(jsonString);

	}

}
