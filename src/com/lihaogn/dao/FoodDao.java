package com.lihaogn.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.lihaogn.domain.FoodCategory;
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

}
