package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoDB;
import com.revature.models.User;
import com.revature.services.UserService;

public class UpdateAccountController {

	private static UserDao uDao = new UserDaoDB();
	private static UserService uServ = new UserService(uDao);
	
	public static void updateAccount(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
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
				
		String password = parsedObj.get("password").asText();
		
		
		int userId = Integer.parseInt(req.getSession().getAttribute("id") + "");
		
		User current = uServ.viewAccountInfo(userId);
		
		current.setPassword(password);
				
		
		uServ.updateAccountInfo(current);
		
		
		res.setStatus(200);
		
		res.getWriter().write(new ObjectMapper().writeValueAsString(current));
	}
	
}
