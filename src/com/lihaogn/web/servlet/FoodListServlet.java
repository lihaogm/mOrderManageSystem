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
import com.lihaogn.vo.PageBean;


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
		
//		// 获得所有菜品
////		List<Food> listFood=service.getAllFood();
////		request.setAttribute("foods", listFood);
//		
//		// 获取页面所需菜品的所有信息
//		List<PageBeanFood> listPageBean=service.getAllFoodInfo();
//
//		request.setAttribute("foods", listPageBean);
		
		// 获得所有菜品种类
//		List<FoodCategory> listFoodCategory=service.getAllFoodCategory();
//		request.setAttribute("foodCategories", listFoodCategory);
//		// 获得荤素种类
//		List<FoodType> listFoodType=service.getAllFoodType();
//		request.setAttribute("foodTypes", listFoodType);
		
//		// 获取菜品总数
////		long foodAllCount=service.getFoodAllCount();
//		long foodAllCount=listPageBean.size();
		
		// 分页展示菜品
		
		// 获取当前页数
		String currentPageStr = request.getParameter("currentPage");
		if (currentPageStr==null) {
			currentPageStr="1";
		}
		int currentPage = Integer.parseInt(currentPageStr);
		// 默认每页10条
		int currentCount=10;
		
		PageBean pageBean=null;
		
		pageBean=service.getPageBean(currentPage,currentCount);
		
		request.setAttribute("pageBean", pageBean);
		
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
