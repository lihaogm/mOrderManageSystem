package com.lihaogn.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lihaogn.domain.FoodCategory;
import com.lihaogn.service.FoodService;
import com.lihaogn.utils.JedisPoolUtils;

import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class FoodCategoryListAjaxServlet
 */
public class FoodCategoryListAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FoodService foodService = new FoodService();
		
		// 先从缓存中查询foodCategoryList 如果有直接使用, 没有再从数据库中查询 存到缓存中
		// 1、获得jedis对象 连接redis数据库
		Jedis jedis = JedisPoolUtils.getJedis();
		String foodCategoryListJson = jedis.get("FoodCategoryListJson");

		// 2、判断foodCategoryListJson是否为空
		if(foodCategoryListJson==null){
			System.out.println("缓存没有数据 查询数据库");
			//准备分类数据
			List<FoodCategory> listFoodCategory=foodService.getAllFoodCategory();
			Gson gson = new Gson();
			foodCategoryListJson = gson.toJson(listFoodCategory);
			jedis.set("FoodCategoryListJson", foodCategoryListJson);
			
			System.out.println("redis中菜品类别json： "+foodCategoryListJson);
		}
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(foodCategoryListJson);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
