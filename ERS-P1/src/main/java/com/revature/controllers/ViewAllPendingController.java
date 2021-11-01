package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbDao;
import com.revature.dao.ReimbDaoDB;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbService;

public class ViewAllPendingController {

	private static ReimbDao rDao = new ReimbDaoDB();
	private static ReimbService rService = new ReimbService(rDao);
	
	public static void viewAllPendingRequests(HttpServletRequest req, HttpServletResponse res) throws IOException, JsonProcessingException {
		
		List<Reimbursement> reimbs = rService.viewAllPendingReimbursements();
		
		res.setStatus(200);
		
		res.getWriter().write(new ObjectMapper().writeValueAsString(reimbs));
	}
	
}
