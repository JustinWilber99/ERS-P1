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

public class LoginController {

	private static UserDao uDao = new UserDaoDB();
	private static UserService uServ = new UserService(uDao);
	
	public static void login(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		StringBuilder buffer = new StringBuilder();
		
		BufferedReader reader = req.getReader();
		
		String line;
		
		while((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		
		
		String data = buffer.toString();
		
		System.out.println(data);
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode parsedObj = mapper.readTree(data);
		
		
		String username = parsedObj.get("username").asText();
		
		String password = parsedObj.get("password").asText();
		
		
		try {
			
			User u = uServ.signIn(username, password);
			
			System.out.println(u);
			
			req.getSession().setAttribute("id", u.getId());
			
			res.setStatus(200);
			
			res.getWriter().write(new ObjectMapper().writeValueAsString(u));
			
			System.out.println(req.getRequestURI());
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
	}
	
}
