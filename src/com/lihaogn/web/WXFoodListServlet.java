package com.lihaogn.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lihaogn.domain.Food;
import com.lihaogn.domain.FoodCategory;
import com.lihaogn.service.FoodService;
import com.lihaogn.vo.PageBeanWXMenu;

/**
 * Servlet implementation class WXFoodListServlet
 */
public class WXFoodListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String idString = request.getParameter("id");
//		System.out.println(idString);
//		
//		String food="id:1,name:lisi";
//		String json=null;
//		
//		if ("1".equals(idString)) {
//			Gson gson = new Gson();
//			json=gson.toJson(food);
//		}
		
		FoodService foodService = new FoodService();
		// 获取所有菜品
		List<Food> allFood = foodService.getAllFood();
		// 获取所有菜品种类
		List<FoodCategory> allFoodCategory = foodService.getAllFoodCategory();
		// 封装微信菜单页面数据
		PageBeanWXMenu wxMenu = new PageBeanWXMenu();
		
		wxMenu.setFoodList(allFood);
		wxMenu.setFoodCategoryList(allFoodCategory);
		
		// 将对象包装成json格式发回
		Gson gson = new Gson();
		String json = gson.toJson(wxMenu);
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
