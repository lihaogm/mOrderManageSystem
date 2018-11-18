package com.lihaogn.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.lihaogn.domain.Food;
import com.lihaogn.domain.FoodCategory;
import com.lihaogn.domain.FoodType;
import com.lihaogn.utils.DataSourceUtils;

public class FoodDao {

	/**
	 * 获取所有菜品类别
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<FoodCategory> getAllFoodCategory() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from food_cook_way_category";
		List<FoodCategory> foodCategories = runner.query(sql, new BeanListHandler<FoodCategory>(FoodCategory.class));
//		System.out.println("sql:"+foodCategories);
		return foodCategories;
	}

	/**
	 * 添加菜品类别
	 * @param foodCategory
	 * @throws SQLException
	 */
	public void addFoodCategory(FoodCategory foodCategory) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into food_cook_way_category values(?,?,?,?)";
		runner.update(sql, foodCategory.getPk_fcwc_id(),foodCategory.getFcwc_name(),
				foodCategory.getFcwc_create_time(),foodCategory.getFcwc_modified_time());
		
	}

	/**
	 * 根据id删除菜品类别
	 * @param fcId
	 * @throws SQLException 
	 */
	public void deleteFoodCategoryById(String fcId) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="delete from food_cook_way_category where pk_fcwc_id=?";
		runner.update(sql, fcId);
		
	}

	/**
	 * 根据id批量删除菜品类别
	 * @param fcIds
	 * @throws SQLException 
	 */
	public void deleteMutilFoodCategoryById(String fcIds) throws SQLException {
		String[] split = fcIds.split("\\)");
		for (String fcid : split) {
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			String sql="delete from food_cook_way_category where pk_fcwc_id=?";
			runner.update(sql, fcid);
		}
		
	}

	/**
	 * 修改菜品类别
	 * @param foodCategory
	 * @throws SQLException 
	 */
	public void editFoodCategory(FoodCategory foodCategory) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update food_cook_way_category set fcwc_name=?,fcwc_modified_time=? where pk_fcwc_id=?";
		runner.update(sql, foodCategory.getFcwc_name(),foodCategory.getFcwc_modified_time(),foodCategory.getPk_fcwc_id());
		
	}

	/**
	 * 根据id获取菜品类名
	 * @param fcId
	 * @return
	 * @throws SQLException 
	 */
	public String getFoodCategoryByid(String fcId) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select fcwc_name from food_cook_way_category where pk_fcwc_id=?";
		String query = (String) runner.query(sql, new ScalarHandler(), fcId);
		return query;
	}

	/**
	 * 获取所有菜品
	 * @return
	 * @throws SQLException 
	 */
	public List<Food> getAllFood() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from food";
		
		return runner.query(sql, new BeanListHandler<Food>(Food.class));
	}

	/**
	 * 获取荤素类别
	 * @return
	 * @throws SQLException 
	 */
	public List<FoodType> getAllFoodType() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from food_type_category";
		
		return runner.query(sql, new BeanListHandler<FoodType>(FoodType.class));
	}

	/*
	 * private String pk_fid;
	private String fname;
	private double fmarket_price;
	private double fshop_price;
	private String fimage;
	private String fcreat_date;
	private String fmodified_date;
	private short fis_hot;
	private String fdesc;
	private short fis_onsale;
	private String fcwc_id;
	private int ftc_id;
	 */
	/*
	 * food[pk_fid: b1f858f4-b35a-466d-837f-914629942b48
fname: 白切鸡
fmarket_price: 22.0
fshop_price: 20.0
fimage:images/food_menu/cold/sauce cucumber.jpg
fcreat_date: 2018-11-18
fmodified_date: 2018-11-18
fis_hot: 0
fdesc: 这就是白切鸡
fis_onsale: 1
fcwc_id: 588fd0fa-e58a-477a-967a-8d52d9d29a85
ftc_id: 3]
	 */
	/**
	 * 添加菜品
	 * @param food
	 * @throws SQLException 
	 */
	public void addFood(Food food) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into food(pk_fid,fname,fmarket_price,fshop_price,"
				+ "fimage,fcreat_date,fmodified_date,fis_hot,fdesc,fis_onsale,fcwc_id,ftc_id)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?)";
		runner.update(sql, 
				food.getPk_fid(),food.getFname(),food.getFmarket_price(),food.getFshop_price(),
				food.getFimage(),food.getFcreat_date(),food.getFmodified_date(),food.getFis_hot(),
				food.getFdesc(),food.getFis_onsale(),food.getFcwc_id(),food.getFtc_id());
	}

}
