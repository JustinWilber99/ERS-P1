package com.revature.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;

public class LogoutController {

	public static void logout(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		req.getSession().invalidate();
		
		res.setStatus(200);
	}
	
}
