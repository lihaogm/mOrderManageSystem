package com.lihaogn.vo;

import java.util.List;

import com.lihaogn.domain.Food;
import com.lihaogn.domain.FoodCategory;

public class PageBeanWXMenu {

	private List<Food> foodList = null;
	private List<FoodCategory> foodCategoryList = null;

	public List<Food> getFoodList() {
		return foodList;
	}

	public void setFoodList(List<Food> foodList) {
		this.foodList = foodList;
	}

	public List<FoodCategory> getFoodCategoryList() {
		return foodCategoryList;
	}

	public void setFoodCategoryList(List<FoodCategory> foodCategoryList) {
		this.foodCategoryList = foodCategoryList;
	}

}
