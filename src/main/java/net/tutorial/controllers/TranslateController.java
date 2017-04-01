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

@WebServlet("/trans")
public class TranslateController extends HttpServlet {
	RequestDispatcher dispatcher;
	DBService db = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/translate.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String text = req.getParameter("tr-from");
		String modelId = req.getParameter("tr-model-id");
		
		TranslatorService lt = new TranslatorService();
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/translate.jsp");
		req.setAttribute("translation", lt.getTranslation(text,modelId));
		req.setAttribute("text", text);
		req.setAttribute("modelId", modelId);
		dispatcher.forward(req, resp);
	}

}
