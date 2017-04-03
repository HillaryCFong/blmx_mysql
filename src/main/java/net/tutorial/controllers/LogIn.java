package net.tutorial.controllers;
//package net.tutorial.utilities;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tutorial.utilities.Account;
import net.tutorial.utilities.AccountManager;
import net.tutorial.utilities.DBService;

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
				//AccountManager accountManager = new AccountManager();
		        String email = req.getParameter("email");
				String password = req.getParameter("password");
				Account account = new Account();
				account.setEmail(email);
				account.setPassword(password);
				
				try {
					if(AccountManager.checkAccount(account)==1){
						//	console.log("true");
						resp.sendRedirect("/trans");
					}
					else 
					resp.sendRedirect("login.jsp");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
	}

}
