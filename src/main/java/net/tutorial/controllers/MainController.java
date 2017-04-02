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

@WebServlet({ "home", "" })
public class MainController extends HttpServlet {
	RequestDispatcher dispatcher;
	DBService db = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String param = req.getParameter("action");
		String id = req.getParameter("id");
		String viewName = "home";

		if (param != null && param.equals("new")) {
			viewName = "contact";
		}
		else if (param != null && param.equals("login")) {
			viewName = "login";
		}
		else if (param != null && param.equals("translate")) {
			viewName = "translate";
		}
		else if (param != null && param.equals("main")) {
			viewName = "main";
		}
		else if (param != null && param.equals("edit")) {
			viewName = "contact";
			db = DBService.getInstance();
			req.setAttribute("document", db.findRecord(Integer.parseInt(id)));

		}
		else {
			db = DBService.getInstance();
			if (param != null && id != null && param.equals("delete")) {
				db.deleteRecord(Integer.parseInt(id));
			}


			req.setAttribute("users", db.allRecords());

		}

		dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/" + viewName + ".jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		record.put("password", password);
		record.put("number", number);
		record.put("gender", gender);

		if (id == null) {
			db.updateRecord(DBService.INSERT_RECORD, record);
		} else {
			record.put("_id", Integer.parseInt(id));
			db.updateRecord(DBService.UPDATE_RECORD, record);
		}

		resp.sendRedirect("home");
	}

}
