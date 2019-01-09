package com.lihaogn.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lihaogn.service.FoodService;

/**
 * Servlet implementation class FoodCategoryMultiDeleteServlet
 */
public class FoodCategoryMultiDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fcIds = request.getParameter("fcids");
		FoodService service = new FoodService();
		service.deleteMutilFoodCategoryById(fcIds);
		response.sendRedirect(request.getContextPath()+"/foodCategoryList");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
