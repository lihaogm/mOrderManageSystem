package com.lihaogn.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lihaogn.domain.FoodCategory;
import com.lihaogn.domain.FoodType;
import com.lihaogn.service.FoodService;
import com.lihaogn.vo.PageBeanFood;

/**
 * Servlet implementation class FoodPreparedEditServlet
 */
public class FoodPreparedEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 获取要查询的fid，查询要更改的food信息并回显
		String fid = request.getParameter("fid");

		FoodService foodService = new FoodService();
		// 获取页面需要的food信息
		PageBeanFood pagefood = foodService.getFoodById(fid);
		request.setAttribute("food", pagefood);
		
		// 获取菜品类别
		List<FoodCategory> foodCategories = foodService.getAllFoodCategory();
		request.setAttribute("foodCategories", foodCategories);
		// 获取荤素类别
		List<FoodType> foodTypes = foodService.getAllFoodType();
		request.setAttribute("foodTypes", foodTypes);
		
		request.getRequestDispatcher("/food_edit.jsp").forward(request, response);
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
