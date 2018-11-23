package com.lihaogn.domain;

public class PageBeanFood {
	
	private Food food;
	private String foodCategoryName;
	private String foodTypeName;
	
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public String getFoodCategoryName() {
		return foodCategoryName;
	}
	public void setFoodCategoryName(String foodCategoryName) {
		this.foodCategoryName = foodCategoryName;
	}
	public String getFoodTypeName() {
		return foodTypeName;
	}
	public void setFoodTypeName(String foodTypeName) {
		this.foodTypeName = foodTypeName;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "pageBeanFood[food: "+food+"\r\n"+"foodCategoryName: "+foodCategoryName+"\r\n"+"foodTypeName"+foodTypeName+"]";
	}

}
