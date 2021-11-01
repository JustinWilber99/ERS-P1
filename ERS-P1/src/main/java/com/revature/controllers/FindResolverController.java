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

public class FindResolverController {

	private static UserDao uDao = new UserDaoDB();
	
	public static void retrieveUsername(HttpServletRequest req, HttpServletResponse res) throws IOException, JsonProcessingException {
		
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
		
		
		int userId = parsedObj.get("resolverId").asInt();
		
		User user = uDao.getUserById(userId);
		
		
		res.setStatus(200);
		
		res.getWriter().write(new ObjectMapper().writeValueAsString(user));
	}
	
}
