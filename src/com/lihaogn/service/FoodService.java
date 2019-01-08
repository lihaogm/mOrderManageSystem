package com.lihaogn.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lihaogn.dao.FoodDao;
import com.lihaogn.domain.Food;
import com.lihaogn.domain.FoodCategory;
import com.lihaogn.domain.FoodType;
import com.lihaogn.vo.Condition;
import com.lihaogn.vo.PageBeanFood;
import com.lihaogn.vo.PageBean;
import com.sun.org.apache.bcel.internal.generic.DALOAD;

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
	public int getFoodAllCount() {
		FoodDao dao = new FoodDao();
		int foodAllCount = 0;
		try {
			foodAllCount = dao.getFoodAllCount();
		} catch (SQLException e) {
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

//				System.out.println(pageBeanFood.toString());
				listPageBeanFood.add(pageBeanFood);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("----------------------");
//		System.out.println(listPageBeanFood.size());
//		for (PageBeanFood bean : listPageBeanFood) {
//			System.out.println(bean.toString());
//		}
		return listPageBeanFood;
	}

	/**
	 * 根据id删除菜品
	 * 
	 * @param fcId
	 */
	public void deleteFoodById(String fcId) {
		FoodDao foodDao = new FoodDao();
		try {
			foodDao.deleteFoodById(fcId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 根据多个id删除对应的菜品
	 * 
	 * @param fcIds
	 */
	public void deleteMutilFoodById(String fcIds) {
		FoodDao foodDao = new FoodDao();
		String[] split = fcIds.split("\\;");
		for (String fcid : split) {
			try {
				foodDao.deleteFoodById(fcid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据id获取菜品
	 * 
	 * @param aid
	 * @return
	 */
	public PageBeanFood getFoodById(String fid) {
		FoodDao foodDao = new FoodDao();
		PageBeanFood beanFood = new PageBeanFood();
		try {
			Food food = foodDao.getFoodById(fid);
			beanFood.setFood(food);
			beanFood.setFoodCategoryName(foodDao.getFoodCategoryByid(food.getFcwc_id()));
			beanFood.setFoodTypeName(foodDao.getFoodTypeNameById(food.getFtc_id()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return beanFood;
	}

	/**
	 * 编辑菜品信息
	 * 
	 * @param food
	 */
	public void editFood(Food food) {

		FoodDao foodDao = new FoodDao();
		try {
			foodDao.editFood(food);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据菜名获取菜品信息
	 * 
	 * @param foodName
	 * @return
	 */
//	public List<PageBeanFood> getFoodByName(String foodName) {
//
//		FoodDao dao = new FoodDao();
//		Food food = null;
//		PageBeanFood pageBeanFood=new PageBeanFood();
//		List<PageBeanFood> listPageBeanFood=new ArrayList<PageBeanFood>();
//		try {
//			food = dao.getFoodByName(foodName);
//			pageBeanFood.setFood(food);
//			pageBeanFood.setFoodCategoryName(dao.getFoodCategoryByid(food.getFcwc_id()));
//			pageBeanFood.setFoodTypeName(dao.getFoodTypeNameById(food.getFtc_id()));
//			listPageBeanFood.add(pageBeanFood);
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return listPageBeanFood;
//	}

	/**
	 * 根据种类获取菜品信息
	 * 
	 * @param foodCategoryId
	 * @param foodTypeId
	 * @param i
	 * @return
	 */
//	public List<PageBeanFood> getFoodByCategoryType(String foodCategoryId, int foodTypeId, int i) {
//		
//		FoodDao dao = new FoodDao();
//		List<PageBeanFood> listPageBeanFood=new ArrayList<PageBeanFood>();
//		List<Food> listfood=null;
//		
//		try {
//			
//			listfood=dao.getFoodByCategoryType(foodCategoryId,foodTypeId,i);
//			
//			for (Food food : listfood) {
//				
//				PageBeanFood pageBeanFood = new PageBeanFood();
//				
//				pageBeanFood.setFood(food);
//				pageBeanFood.setFoodCategoryName(dao.getFoodCategoryByid(food.getFcwc_id()));
//				pageBeanFood.setFoodTypeName(dao.getFoodTypeNameById(food.getFtc_id()));
//				
//				listPageBeanFood.add(pageBeanFood);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return listPageBeanFood;
//	}

	/**
	 * 搜索的菜品记录条数
	 * 
	 * @param foodCategoryId
	 * @param foodTypeId
	 * @param i
	 * @return
	 */
//	public long getItemCount(String foodCategoryId, int foodTypeId, int i) {
//		FoodDao dao = new FoodDao();
//		long itemCount=0;
//		try {
//			itemCount = dao.getItemCount(foodCategoryId,foodTypeId,i);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return itemCount;
//	}

	/**
	 * 按条件搜索菜品
	 * 
	 * @param condition
	 * @return
	 */
	public List<PageBeanFood> getFoodListByCondition(Condition condition) {
		FoodDao foodDao = new FoodDao();
		List<PageBeanFood> listPageBeanFood = new ArrayList<PageBeanFood>();
		try {
			List<Food> foodList = foodDao.getFoolListByConditon(condition);
			for (Food food : foodList) {
				PageBeanFood pageBeanFood = new PageBeanFood();
				pageBeanFood.setFood(food);
				pageBeanFood.setFoodCategoryName(foodDao.getFoodCategoryByid(food.getFcwc_id()));
				pageBeanFood.setFoodTypeName(foodDao.getFoodTypeNameById(food.getFtc_id()));

				listPageBeanFood.add(pageBeanFood);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listPageBeanFood;
	}

	/**
	 * 获取每页菜品
	 * 
	 * @param currentPage
	 * @param currentCount
	 * @return
	 */
	public PageBean getPageBean(int currentPage, int currentCount) {

		FoodDao foodDao = new FoodDao();

		PageBean pageBean = new PageBean();
		List<PageBeanFood> listPageBeanFood = new ArrayList<PageBeanFood>();

		try {
			// 当前页
			pageBean.setCurrentPage(currentPage);
			// 当前页显示条数
			pageBean.setCurrentCount(currentCount);

			// 总条数
			int totalCount = foodDao.getFoodAllCount();
			pageBean.setTotalCount(totalCount);
			// 总页数
			int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
			pageBean.setTotalPage(totalPage);
			// 每页显示 的数据
			int index = (currentPage - 1) * currentCount;
			// 获取每页的菜品列表
			List<Food> foodList = foodDao.getFoodListByPage(index, currentCount);

			for (Food food : foodList) {
				PageBeanFood pageBeanFood = new PageBeanFood();
				pageBeanFood.setFood(food);
				pageBeanFood.setFoodCategoryName(foodDao.getFoodCategoryByid(food.getFcwc_id()));
				pageBeanFood.setFoodTypeName(foodDao.getFoodTypeNameById(food.getFtc_id()));

				listPageBeanFood.add(pageBeanFood);
			}
			pageBean.setFoodList(listPageBeanFood);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pageBean;
	}

}
