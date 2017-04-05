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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;



import net.tutorial.utilities.DBService;
import net.tutorial.utilities.Account;
import net.tutorial.utilities.AccountManager;
import net.tutorial.utilities.TranslatorService;

@WebServlet(urlPatterns={"/register", "/login", "/logout","/trans",""})

public class MainController extends HttpServlet {
	RequestDispatcher dispatcher;
	DBService db = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String param = req.getParameter("action");
		String id = req.getParameter("id");
		String viewName = "home";

		if (param != null && param.equals("register")) {
			viewName = "register";
		}
		/*else if (param != null && param.equals("login")) {
			viewName = "login";
		}*/
		/*else if (param != null && param.equals("translate")) {
			viewName = "translate";
		}*/
		else if (param != null && param.equals("login")) {
			viewName = "login";
		}
		
		else if (param != null && param.equals("translate")) {
			viewName = "translate";
		}
		
		else if (param != null && param.equals("logout")) {
			viewName = "login";
		}
		
		else if (param != null && param.equals("edit")) {
			viewName = "contact";
			db = DBService.getInstance();
			req.setAttribute("document", db.findRecord(Integer.parseInt(id)));
			viewName="login";
		}
		else {
			db = DBService.getInstance();
			if (param != null && id != null && param.equals("delete")) {
				db.deleteRecord(Integer.parseInt(id));
				viewName="login";
			}


			req.setAttribute("users", db.allRecords());
			
		}

		dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/" + viewName + ".jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AccountManager accountManager = new AccountManager();
		Cookie[] cookieList = req.getCookies();
    	RequestDispatcher dispatcher;
	
		switch(req.getServletPath()){
		case "/register":
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Integer number = Integer.parseInt(req.getParameter("number"));
		String gender = req.getParameter("gender");

		Map<String, Object> record = new HashMap<String, Object>();
		DBService db = DBService.getInstance();

		record.put("name", name);
		record.put("email", email);
		record.put("password", accountManager.hashPassword(password));
		record.put("number", number);
		record.put("gender", gender);

		if (id == null) {
			db.updateRecord(DBService.INSERT_RECORD, record);
		} else {
			record.put("_id", Integer.parseInt(id));
			db.updateRecord(DBService.UPDATE_RECORD, record);
		}

		resp.sendRedirect("/login");
		
		break;
		
		case "/login":
			    String email2 = req.getParameter("email");
				String password2 = req.getParameter("password");
				Account account = new Account();
				account.setEmail(email2);
				account.setPassword(accountManager.hashPassword(password2));
				System.out.println(accountManager.hashPassword(password2));
				
				System.out.println(accountManager.checkAccount(account));
				
			
				try {
					if(accountManager.checkAccount(account)){
						//	console.log("true");
						
					HttpSession session = req.getSession();
					session.setAttribute("Valid", true);
						
					session.setAttribute("Email", email2);
					
					Cookie c = new Cookie("Email", email2);
					c.setMaxAge(60*15);
					resp.addCookie(c);
						
					dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/translate.jsp");
	            	dispatcher.forward(req, resp);
					}
					else {
						req.getSession().setAttribute("Error", "Error");
					
					dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
					dispatcher.forward(req, resp);
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			
		case "/logout":
		
		//		req.getSession().invalidate();
				
				for(Cookie c: cookieList){
					if(c.getName().equals("email")){
						c.setMaxAge(0);
						resp.addCookie(c);
						break;
					}	
				}
				
				dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
				dispatcher.forward(req, resp);
				break;
				
		case "/trans":
		String text = req.getParameter("tr-from");
		String modelId = req.getParameter("tr-model-id");
		
		TranslatorService lt = new TranslatorService();
		RequestDispatcher dispatcher2 = getServletContext().getRequestDispatcher("/WEB-INF/views/translate.jsp");
		req.setAttribute("translation", lt.getTranslation(text,modelId));
		req.setAttribute("text", text);
		req.setAttribute("modelId", modelId);
		dispatcher2.forward(req, resp);
		break;
	}
}


}
