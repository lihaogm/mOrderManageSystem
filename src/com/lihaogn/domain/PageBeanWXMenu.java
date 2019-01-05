package com.lihaogn.domain;

import java.util.List;

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
