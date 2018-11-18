package com.lihaogn.service;

import java.sql.SQLException;
import java.util.List;

import com.lihaogn.dao.FoodDao;
import com.lihaogn.domain.Food;
import com.lihaogn.domain.FoodCategory;
import com.lihaogn.domain.FoodType;

public class FoodService {

	/**
	 * 获取所有菜品类别
	 * 
	 * @return
	 */
	public List<FoodCategory> getAllFoodCategory() {
		FoodDao dao = new FoodDao();
		List<FoodCategory> listFoodGategory = null;
		try {
			listFoodGategory = dao.getAllFoodCategory();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listFoodGategory;
	}

	/**
	 * 添加菜品类别
	 * 
	 * @param foodCategory
	 */
	public void addFoodCategory(FoodCategory foodCategory) {
		FoodDao dao = new FoodDao();
		try {
			dao.addFoodCategory(foodCategory);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 根据id删除菜品类别
	 * 
	 * @param fcId
	 */
	public void deleteFoodCategoryById(String fcId) {
		FoodDao dao = new FoodDao();
		try {
			dao.deleteFoodCategoryById(fcId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 根据id批量删除菜品类别
	 * 
	 * @param fcIds
	 */
	public void deleteMutilFoodCategoryById(String fcIds) {
		FoodDao dao = new FoodDao();
		try {
			dao.deleteMutilFoodCategoryById(fcIds);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 根据id获取菜品类名
	 * 
	 * @param fcId
	 * @return
	 */
	public String getFoodCategoryByid(String fcId) {
		FoodDao dao = new FoodDao();
		String fcName = null;
		try {
			fcName = dao.getFoodCategoryByid(fcId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fcName;
	}

	/**
	 * 修改菜品类别
	 * 
	 * @param foodCategory
	 */
	public void editFoodCategory(FoodCategory foodCategory) {
		FoodDao dao = new FoodDao();
		try {
			dao.editFoodCategory(foodCategory);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取所有菜品
	 * 
	 * @return
	 */
	public List<Food> getAllFood() {
		FoodDao dao = new FoodDao();
		List<Food> listFood = null;
		try {
			listFood = dao.getAllFood();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listFood;
	}

	/**
	 * 获取荤素类别
	 * 
	 * @return
	 */
	public List<FoodType> getAllFoodType() {
		FoodDao dao = new FoodDao();
		List<FoodType> listFoodType = null;
		try {
			listFoodType = dao.getAllFoodType();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listFoodType;
	}

	/**
	 * 添加菜品
	 * @param food
	 */
	public void addFood(Food food) {
		FoodDao dao = new FoodDao();
		try {
			dao.addFood(food);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
