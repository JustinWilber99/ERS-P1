package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbDao;
import com.revature.dao.ReimbDaoDB;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbService;

public class SubmitReimbController {

	private static ReimbDao rDao = new ReimbDaoDB();
	private static ReimbService rServ = new ReimbService(rDao);
	
	public static void submitRequest(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
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
		
		
		double amount = parsedObj.get("amount").asDouble();
		
		int typeId = parsedObj.get("type").asInt();
		
		String description = parsedObj.get("description").asText();
		
		int authorId = Integer.parseInt(req.getSession().getAttribute("id") + "");
		
		
		Reimbursement reimb = new Reimbursement(amount, authorId, typeId);
		
		
		reimb.setDescription(description);
		
		rServ.submitReimbursement(reimb);
		
		
		res.setStatus(200);
		
		res.getWriter().write(data);
	}
	
}
