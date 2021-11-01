package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MasterServlet extends HttpServlet {

	private static final long serialVersionUID = 6491822895140340931L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		Dispatcher.process(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		Dispatcher.process(req, res);
	}

}
