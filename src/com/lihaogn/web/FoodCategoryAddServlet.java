package com.lihaogn.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lihaogn.domain.FoodCategory;
import com.lihaogn.service.FoodService;

/**
 * Servlet implementation class FoodCategoryAddServlet
 */
public class FoodCategoryAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String foodCategoryName = request.getParameter("category_name");
		FoodService service = new FoodService();
		FoodCategory foodCategory = new FoodCategory();
		
		foodCategory.setPk_fcwc_id(UUID.randomUUID().toString());
		foodCategory.setFcwc_name(foodCategoryName);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String createDate = dateFormat.format(new Date());
		foodCategory.setFcwc_create_time(createDate);
		foodCategory.setFcwc_modified_time(createDate);
		
		service.addFoodCategory(foodCategory);
		
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
