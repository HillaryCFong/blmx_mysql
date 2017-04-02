package net.tutorial.controllers;

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

import net.tutorial.controllers.Account;
import net.tutorial.controllers.AccountManager;

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
		        String username = request.getParameter("username");
				String password = request.getParameter("password");
				account = new Account();
				account.setUsername(username);
				account.setPassword(password);
				
			
				if(accountManager.checkAccount(account))
				response.sendRedirect("translate.jsp");
				else 
				response.sendRedirect("login.jsp");

		
	}

}
