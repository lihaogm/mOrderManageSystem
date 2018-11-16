package com.lihaogn.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lihaogn.domain.FoodCategory;
import com.lihaogn.service.FoodService;

/**
 * Servlet implementation class FoodCategoryUpdateServlet
 */
public class FoodCategoryUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String fcId = request.getParameter("fcid");
		String categoryName = request.getParameter("category_name");
		
		FoodCategory foodCategory = new FoodCategory();
		foodCategory.setPk_fcwc_id(fcId);
		foodCategory.setFcwc_name(categoryName);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String modifiedDate = dateFormat.format(new Date());
		foodCategory.setFcwc_modified_time(modifiedDate);
		
		FoodService service = new FoodService();
		service.editFoodCategory(foodCategory);
		
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
