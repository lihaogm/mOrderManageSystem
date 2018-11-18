package com.lihaogn.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lihaogn.domain.FoodCategory;
import com.lihaogn.domain.FoodType;
import com.lihaogn.service.FoodService;

/**
 * Servlet implementation class FoodPreparedAddServlet
 */
public class FoodPreparedAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FoodService foodService = new FoodService();
		List<FoodCategory> listFoodCategory = foodService.getAllFoodCategory();
		List<FoodType> listFoodType = foodService.getAllFoodType();
		
		request.setAttribute("foodCategories", listFoodCategory);
		request.setAttribute("foodTypes", listFoodType);
		
		request.getRequestDispatcher("/food_add.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
