package com.lihaogn.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
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
		String sql="select * from food_cook_way_category order by pk_fcwc_id";
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
		String sql="select * from food order by fcwc_id";
		
		return runner.query(sql, new BeanListHandler<Food>(Food.class));
	}

	/**
	 * 获取所有荤素类别
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
	 * @param foodTypeId 
	 * @param foodCategoryId 
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

	/**
	 * 获取菜品总数
	 * @return
	 * @throws SQLException 
	 */
	public long getFoodAllCount() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select count(*) from food";
		
		return (long) runner.query(sql, new ScalarHandler());
	}

	/**
	 * 根据id获取荤素类别名称
	 * @param ftc_id
	 * @return
	 * @throws SQLException 
	 */
	public String getFoodTypeNameById(int ftc_id) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select ftc_name from food_type_category where pk_ftc_id=?";
		return (String) runner.query(sql, new ScalarHandler(),ftc_id);
	}

	/**
	 * 根据id删除菜品
	 * @param fcId
	 * @throws SQLException 
	 */
	public void deleteFoodById(String fcId) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="delete from food where pk_fid=?";
		runner.update(sql, fcId);
	}

	/**
	 * 根据id获取菜品
	 * @param aid
	 * @return
	 * @throws SQLException 
	 */
	public Food getFoodById(String fcid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from food where pk_fid=?";
		
		return runner.query(sql, new BeanHandler<Food>(Food.class), fcid);
	}

	/**
	 * 编辑菜品信息
	 * @param food
	 * @throws SQLException 
	 */
	public void editFood(Food food) throws SQLException {

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update food set fname=?,fmarket_price=?,fshop_price=?,fimage=?,fmodified_date=?,"
				+ "fdesc=?,fis_onsale=?,fcwc_id=?,ftc_id=? where pk_fid=?";
		runner.update(sql, 
				food.getFname(),food.getFmarket_price(),food.getFshop_price(),food.getFimage(),food.getFmodified_date(),
				food.getFdesc(),food.getFis_onsale(),food.getFcwc_id(),food.getFtc_id(),food.getPk_fid());
	}

	/**
	 * 根据菜名获取菜品信息
	 * @param foodName
	 * @return
	 * @throws SQLException 
	 */
	public Food getFoodByName(String foodName) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from food where fname=?";
		
		return runner.query(sql, new BeanHandler<Food>(Food.class), foodName);
	}

	/**
	 * 根据种类获取菜品信息
	 * @param foodCategoryId
	 * @param foodTypeId
	 * @param i 
	 * @return
	 * @throws SQLException 
	 */
	public List<Food> getFoodByCategoryType(String foodCategoryId, int foodTypeId, int i) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql=null;
		List<Food> foods=null;
		if (i==0) {
			sql="select * from food where fcwc_id=? and ftc_id=?";
			foods = runner.query(sql, new BeanListHandler<Food>(Food.class), 
					foodCategoryId,foodTypeId);
		}else if (i==1) {
			sql="select * from food where fcwc_id=?";
			foods = runner.query(sql, new BeanListHandler<Food>(Food.class), 
					foodCategoryId);
		}else if (i==2) {
			sql="select * from food where ftc_id=?";
			foods = runner.query(sql, new BeanListHandler<Food>(Food.class), 
					foodTypeId);
		}
		
		return foods;
	}

	/**
	 * 搜索的菜品记录条数
	 * @param foodCategoryId
	 * @param foodTypeId
	 * @param i
	 * @return
	 * @throws SQLException 
	 */
	public long getItemCount(String foodCategoryId, int foodTypeId, int i) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql=null;
		long itemCount=0;
		
		if (i==0) {
			sql="select count(*) from food where fcwc_id=? and ftc_id=?";
			itemCount=(long) runner.query(sql, new ScalarHandler(), foodCategoryId,foodTypeId);
		}else if (i==1) {
			sql="select count(*) from food where fcwc_id=?";
			itemCount=(long) runner.query(sql, new ScalarHandler(), foodCategoryId);
		}else if (i==2) {
			sql="select count(*) from food where ftc_id=?";
			itemCount=(long) runner.query(sql, new ScalarHandler(), foodTypeId);
		}
		
		return itemCount;
	}

}
