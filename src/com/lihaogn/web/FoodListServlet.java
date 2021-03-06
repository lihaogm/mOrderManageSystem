package com.lihaogn.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lihaogn.domain.FoodCategory;
import com.lihaogn.domain.FoodType;
import com.lihaogn.domain.PageBeanFood;
import com.lihaogn.service.FoodService;


/**
 * Servlet implementation class FoodListServlet
 */
public class FoodListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FoodService service = new FoodService();
		
		// 获得所有菜品
//		List<Food> listFood=service.getAllFood();
//		request.setAttribute("foods", listFood);
		
		// 获取页面所需菜品的所有信息
		List<PageBeanFood> listPageBean=service.getAllFoodInfo();

		request.setAttribute("foods", listPageBean);
		// 获得所有菜品种类
		List<FoodCategory> listFoodCategory=service.getAllFoodCategory();
		request.setAttribute("foodCategories", listFoodCategory);
		// 获得荤素种类
		List<FoodType> listFoodType=service.getAllFoodType();
		request.setAttribute("foodTypes", listFoodType);
		// 获取菜品总数
		long foodAllCount=service.getFoodAllCount();
		request.setAttribute("foodCounts", foodAllCount);
		
		request.getRequestDispatcher("/food_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
