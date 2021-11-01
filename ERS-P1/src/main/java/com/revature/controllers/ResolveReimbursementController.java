package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbDao;
import com.revature.dao.ReimbDaoDB;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbService;

public class ResolveReimbursementController {

	private static ReimbDao rDao = new ReimbDaoDB();
	private static ReimbService rServ = new ReimbService(rDao);
	
	public static void resolveReimbursement(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
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
		
		
		int reimbId = parsedObj.get("id").asInt();
		
		int statusId = parsedObj.get("statusId").asInt();
		
		int resolverId = Integer.parseInt(req.getSession().getAttribute("id") + "");
		
		
		Reimbursement reimb = rDao.getReimbursementById(reimbId);
		
		reimb.setResolverId(resolverId);
		
		reimb.setTimeResolved(new Timestamp(System.currentTimeMillis()));
		
		reimb.setStatusId(statusId);
		
		
		rServ.resolveReimbursement(reimb);
		
		res.setStatus(200);
		
		res.getWriter().write(new ObjectMapper().writeValueAsString(reimb));
		
	}
	
}
