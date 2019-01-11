package com.lihaogn.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lihaogn.domain.FoodCategory;
import com.lihaogn.domain.FoodType;
import com.lihaogn.service.FoodService;
import com.lihaogn.utils.JedisPoolUtils;

import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class FoodTypeListAjaxServlet
 */
public class FoodTypeListAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
FoodService foodService = new FoodService();
		
		// 先从缓存中查询foodCategoryList 如果有直接使用, 没有再从数据库中查询 存到缓存中
		// 1、获得jedis对象 连接redis数据库
		Jedis jedis = JedisPoolUtils.getJedis();
		String foodTypeListJson=jedis.get("FoodTypeListJson");
	
		if(foodTypeListJson==null){
			System.out.println("缓存没有数据 查询数据库");
			//准备分类数据
			List<FoodType> listFoodType=foodService.getAllFoodType();
			Gson gson = new Gson();
			foodTypeListJson = gson.toJson(listFoodType);
			jedis.set("FoodTypeListJson", foodTypeListJson);
			
			System.out.println("redis中菜品荤素类别json： "+foodTypeListJson);
		}
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(foodTypeListJson);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
