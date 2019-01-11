package com.lihaogn.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lihaogn.domain.FoodCategory;
import com.lihaogn.service.FoodService;

/**
 * Servlet implementation class FoodCategoryListServlet
 */
public class FoodCategoryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FoodService service=new FoodService();
		
		List<FoodCategory> listFoodCategory=service.getAllFoodCategory();
//		for (FoodCategory foodCategory : listFoodCategory) {
//			System.out.println(foodCategory);
//		}
		request.setAttribute("foodCategories", listFoodCategory);
		request.getRequestDispatcher("/food_category.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
