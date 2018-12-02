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
 * Servlet implementation class FoodSearchServlet
 */
public class FoodSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		FoodService foodService = new FoodService();
		// 获得所有菜品种类
		List<FoodCategory> listFoodCategory=foodService.getAllFoodCategory();
		request.setAttribute("foodCategories", listFoodCategory);
		// 获得荤素种类
		List<FoodType> listFoodType=foodService.getAllFoodType();
		request.setAttribute("foodTypes", listFoodType);
		
		// 获取菜品种类
		String foodCategoryId = request.getParameter("foodcook_category");
		// 获取荤素种类
		int foodTypeId = 0;
		String foodType = request.getParameter("foodtype_category");
		if ("".equals(foodType)) {
			foodTypeId = 4;
		}else
			foodTypeId=Integer.parseInt(foodType);
		// 获取输入的菜品名
		String foodName = request.getParameter("foodname");
		
		
		// 输入的菜名和选择的种类分开起作用
		if (!"".equals(foodName)) {
			List<PageBeanFood> listFoodPage  = foodService.getFoodByName(foodName);
			request.setAttribute("foods", listFoodPage);
			request.setAttribute("foodCounts", 1);
		}else if ((!"".equals(foodCategoryId)) && foodTypeId!=4) { // 类别都选了
			List<PageBeanFood> listFoodPage = foodService.getFoodByCategoryType(foodCategoryId, foodTypeId,0);
			request.setAttribute("foods", listFoodPage);
			// 获取记录条数
			long itemCount=foodService.getItemCount(foodCategoryId, foodTypeId,0);
			request.setAttribute("foodCounts", itemCount);
		}else if ((!"".equals(foodCategoryId)) && foodTypeId==4) { // 只选了菜品类别
			List<PageBeanFood> listFoodPage = foodService.getFoodByCategoryType(foodCategoryId, foodTypeId,1);
			request.setAttribute("foods", listFoodPage);
			// 获取记录条数
			long itemCount=foodService.getItemCount(foodCategoryId, foodTypeId,1);
			request.setAttribute("foodCounts", itemCount);
		}else if("".equals(foodCategoryId) && foodTypeId!=4) { // 只选了荤素类别
			List<PageBeanFood> listFoodPage = foodService.getFoodByCategoryType(foodCategoryId, foodTypeId,2);
			request.setAttribute("foods", listFoodPage);	
			// 获取记录条数
			long itemCount=foodService.getItemCount(foodCategoryId, foodTypeId,2);
			request.setAttribute("foodCounts", itemCount);
		}else {
			response.sendRedirect(request.getContextPath()+"/foodList");
			return;
		}
			
		request.getRequestDispatcher("/food_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
