package com.lihaogn.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.lihaogn.domain.Food;
import com.lihaogn.service.FoodService;

/**
 * Servlet implementation class FoodAddServlet
 */
public class FoodAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		// 1 封装数据
		Map<String, String[]> properties = request.getParameterMap();
		Food food = new Food();
		try {
			BeanUtils.populate(food, properties);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 设置id
		food.setPk_fid(UUID.randomUUID().toString());
		// 设置修改时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String createDate = dateFormat.format(new Date());
		food.setFmodified_date(createDate);
		food.setFcreat_date(createDate);
		
		System.out.println(food);
		
//		FoodService service = new FoodService();
//		service.addFood(food);
		
		response.sendRedirect(request.getContextPath()+"/foodList");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
