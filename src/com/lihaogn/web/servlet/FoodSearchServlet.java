package com.lihaogn.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.lihaogn.domain.FoodCategory;
import com.lihaogn.domain.FoodType;
import com.lihaogn.service.FoodService;
import com.lihaogn.vo.Condition;
import com.lihaogn.vo.PageBean;
import com.lihaogn.vo.PageBeanFood;

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
		
		// 1 收集表单数据
		Map<String, String[]> properties = request.getParameterMap();
		// 2 将数据封装到vo实体中
		Condition condition = new Condition();
		try {
			BeanUtils.populate(condition, properties);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 判断搜索条件是否为空，为空直接返回列表页
		if ("".equals(condition.getFoodCookCategory())&&"".equals(condition.getFoodTypeCategory())&&"".equals(condition.getFoodName().trim())) {
			response.sendRedirect(request.getContextPath()+"/foodList");
			return;
		}
		
		// 获取当前页数
		String currentPageStr = request.getParameter("currentPage");
		if (currentPageStr==null) {
			currentPageStr="1";
		}
		int currentPage = Integer.parseInt(currentPageStr);
		// 默认每页10条
		int currentCount=10;
		
		// 3 将实体传递给service层
		FoodService foodService = new FoodService();
		
		PageBean pageBean=null;
		
		pageBean=foodService.getPageBeanByCondition(currentPage,currentCount,condition);
		
		request.setAttribute("pageBean", pageBean);
		
		// 获得所有菜品种类
		List<FoodCategory> listFoodCategory=foodService.getAllFoodCategory();
		request.setAttribute("foodCategories", listFoodCategory);
		
		// 获得荤素种类
		List<FoodType> listFoodType=foodService.getAllFoodType();
		request.setAttribute("foodTypes", listFoodType);

		request.setAttribute("condition", condition);
		
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
