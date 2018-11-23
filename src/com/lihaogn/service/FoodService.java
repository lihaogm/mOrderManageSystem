package com.lihaogn.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lihaogn.dao.FoodDao;
import com.lihaogn.domain.Food;
import com.lihaogn.domain.FoodCategory;
import com.lihaogn.domain.FoodType;
import com.lihaogn.domain.PageBeanFood;

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
	 * 
	 * @param food
	 * @param foodTypeId
	 * @param foodCategoryId
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

	/**
	 * 获取菜品总数
	 * 
	 * @return
	 */
	public long getFoodAllCount() {
		FoodDao dao = new FoodDao();
		long foodAllCount = 0;
		try {
			foodAllCount = dao.getFoodAllCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return foodAllCount;
	}

	/**
	 * 获取页面所需菜品的所有信息
	 * 
	 * @return
	 */
	public List<PageBeanFood> getAllFoodInfo() {
		FoodDao dao = new FoodDao();

		List<PageBeanFood> listPageBeanFood = new ArrayList<PageBeanFood>();
		

		// 获取到所有的菜品
		List<Food> listAllFood = null;
		try {
			listAllFood = dao.getAllFood();
			// 向列表中添加信息
			for (Food food : listAllFood) {
				PageBeanFood pageBeanFood = new PageBeanFood();
				// 封装food信息
				pageBeanFood.setFood(food);

				// 获取对应的类别信息
				String fcwc_id = food.getFcwc_id();
				pageBeanFood.setFoodCategoryName(dao.getFoodCategoryByid(fcwc_id));
				// 获取对应的荤素信息
				int ftc_id = food.getFtc_id();
				pageBeanFood.setFoodTypeName(dao.getFoodTypeNameById(ftc_id));

				System.out.println(pageBeanFood.toString());
				listPageBeanFood.add(pageBeanFood);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("----------------------");
		System.out.println(listPageBeanFood.size());
		for (PageBeanFood bean : listPageBeanFood) {
			System.out.println(bean.toString());
		}
		return listPageBeanFood;
	}

}
