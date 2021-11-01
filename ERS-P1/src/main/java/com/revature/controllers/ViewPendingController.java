package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbDao;
import com.revature.dao.ReimbDaoDB;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoDB;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbService;

public class ViewPendingController {
	
	private static ReimbDao rDao = new ReimbDaoDB();
	private static ReimbService rServ = new ReimbService(rDao);
	private static UserDao uDao =new UserDaoDB();
	
	public static void viewPendingRequests(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		int userId = Integer.parseInt(req.getSession().getAttribute("id") + "");
		
		User current = uDao.getUserById(userId);
		
		List<Reimbursement> reimbs = rServ.viewPendingReimbursementsByUser(current);
		
		res.setStatus(200);
		
		res.getWriter().write(new ObjectMapper().writeValueAsString(reimbs));
	}
	
}
