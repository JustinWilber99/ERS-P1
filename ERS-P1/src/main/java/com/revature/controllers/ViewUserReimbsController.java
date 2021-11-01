package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbDao;
import com.revature.dao.ReimbDaoDB;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbService;

public class ViewUserReimbsController {

	private static ReimbDao rDao = new ReimbDaoDB();
	private static ReimbService rService = new ReimbService(rDao);
	
	public static void viewUserRequests(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line;
		
		while((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		
		
		String data = buffer.toString();
		
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode parsedObj = mapper.readTree(data);
		
		
		
		int id = parsedObj.get("id").asInt();
		
		String username = parsedObj.get("username").asText();
		
		String password = parsedObj.get("password").asText();
		
		String firstName = parsedObj.get("firstName").asText();
		
		String lastName = parsedObj.get("lastName").asText();
		
		String email = parsedObj.get("email").asText();
		
		int roleId = parsedObj.get("roleId").asInt();
		
		
		User u = new User(id, username, password, firstName, lastName, email, roleId);
		
		
		List<Reimbursement> reimbs = rService.viewAllReimbursementsBy(u);
		
		res.setStatus(200);
		
		res.getWriter().write(new ObjectMapper().writeValueAsString(reimbs));
	}
	
}
