package com.lihaogn.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lihaogn.domain.Food;
import com.lihaogn.domain.FoodCategory;
import com.lihaogn.domain.FoodType;
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
		
//		String mfoodCategory=null;
//		String mfoodType=null;
		
		FoodService service = new FoodService();
		
		List<Food> listFood=service.getAllFood();
		
		List<FoodCategory> listFoodCategory=service.getAllFoodCategory();
		
		List<FoodType> listFoodType=service.getAllFoodType();
		
		String[] mfoodTypes=new String[listFoodType.size()];
		String[] mfoodCategories=new String[listFoodCategory.size()];
		
		for (Food food : listFood) {
			for (FoodType foodType : listFoodType) {
				for (String mfoodType : mfoodTypes) {
					if (food.getFtc_id()==foodType.getPk_ftc_id()) {
						mfoodType=foodType.getFtc_name();
					}
				}
				
			}
			for (FoodCategory foodCategory : listFoodCategory) {
				for (String mfoodCategory : mfoodCategories) {
					if (food.getFcwc_id().equals(foodCategory.getPk_fcwc_id())) {
						mfoodCategory=foodCategory.getFcwc_name();
					}
				}
				
			}
		}
		
		
		request.setAttribute("mfoodCategory", mfoodCategories);
		request.setAttribute("mfoodType", mfoodTypes);
		request.setAttribute("foodTypes", listFoodType);
		request.setAttribute("foodCategories", listFoodCategory);
		request.setAttribute("foods", listFood);
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
