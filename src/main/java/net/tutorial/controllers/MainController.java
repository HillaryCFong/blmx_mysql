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


			req.setAttribute("translations", db.allRecords());

		}
		
		if (param != null && param.equals("login")) {
			viewName = "login";
		}

		dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/" + viewName + ".jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String language = req.getParameter("language");
		String old = req.getParameter("old");
		String newt = req.getParameter("newt");
		Integer thumbsup = Integer.parseInt(req.getParameter("thumbsup"));
		Integer thumbsdown = Integer.parseInt(req.getParameter("thumbsdown"));

		Map<String, Object> record = new HashMap<String, Object>();
		DBService db = DBService.getInstance();

		record.put("language", language);
		record.put("old", old);
		record.put("newt", newt);
		record.put("thumbsup", thumbsup);
		record.put("thumbsdown", thumbsdown);

		if (id == null) {
			db.updateRecord(DBService.INSERT_RECORD, record);
		} else {
			record.put("_id", Integer.parseInt(id));
			db.updateRecord(DBService.UPDATE_RECORD, record);
		}

		resp.sendRedirect("home");
	}

}
