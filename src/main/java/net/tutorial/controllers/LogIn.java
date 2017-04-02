package net.tutorial.controllers;
//package net.tutorial.utilities;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tutorial.utilities.DBService;
import net.tutorial.utilities.TranslatorService;

import net.tutorial.utilities.Account;
import net.tutorial.utilities.AccountManager;

@WebServlet("/login")
public class LogIn extends HttpServlet {
	RequestDispatcher dispatcher;
	DBService db = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
				AccountManager accountManager = new AccountManager();
		        String username = req.getParameter("username");
				String password = req.getParameter("password");
				account = new Account();
				account.setUsername(username);
				account.setPassword(password);
				
			
				if(accountManager.checkAccount(account))
				resp.sendRedirect("translate.jsp");
				else 
				resp.sendRedirect("login.jsp");

		
	}

}
