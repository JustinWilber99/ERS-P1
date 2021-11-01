package com.revature.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoDB;
import com.revature.models.User;
import com.revature.services.UserService;

public class ViewAccountController {

	private static UserDao uDao = new UserDaoDB();
	private static UserService uServ = new UserService(uDao);
	
	public static void viewAccount(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		int userId = Integer.parseInt(req.getSession().getAttribute("id") + "");
		
		User u = uServ.viewAccountInfo(userId);
		
		res.setStatus(200);
		
		res.getWriter().write(new ObjectMapper().writeValueAsString(u));
	}
	
}
