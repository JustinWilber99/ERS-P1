package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoDB;
import com.revature.models.User;
import com.revature.services.UserService;

public class ViewEmployeesController {

	private static UserDao uDao = new UserDaoDB();
	private static UserService uService = new UserService(uDao);
	
	public static void viewAllEmployees(HttpServletRequest req, HttpServletResponse res) throws IOException, JsonProcessingException {
		
		List<User> employees = uService.viewAllEmployees();
		
		res.setStatus(200);
		
		res.getWriter().write(new ObjectMapper().writeValueAsString(employees));
	}
	
}
