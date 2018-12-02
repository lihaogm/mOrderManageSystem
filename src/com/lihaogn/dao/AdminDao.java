package com.lihaogn.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.lihaogn.domain.Admin;
import com.lihaogn.utils.DataSourceUtils;

public class AdminDao {

	/**
	 * 用户登录校验
	 * 
	 * @param adminName
	 * @param adminPassword
	 * @return
	 * @throws SQLException
	 */
	public Admin login(String adminName, String adminPassword) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from admin where adminName=? and adminPassword=?";
		return queryRunner.query(sql, new BeanHandler<Admin>(Admin.class), adminName, adminPassword);
	}

	/**
	 * 获取所有admin信息
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Admin> getAllAdmin() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from admin";
		return runner.query(sql, new BeanListHandler<Admin>(Admin.class));
	}

	/**
	 * 添加一个admin
	 * 
	 * @param admin
	 * @throws SQLException
	 */
	public void addAdmin(Admin admin) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into admin values(?,?,?,?,?)";
		runner.update(sql, admin.getAid(), admin.getAdminName(), admin.getAdminPassword(), admin.getAdminPrivilege(),
				admin.getCreate_time());

	}

	/**
	 * 根据aid删除一个admin
	 * 
	 * @param aid
	 * @throws SQLException
	 */
	public void deleteAdminByAid(String aid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from admin where aid=?";
		runner.update(sql, aid);

	}

	/**
	 * 根据aid查询admin
	 * 
	 * @param aid
	 * @return
	 * @throws SQLException
	 */
	public Admin getAdminByAid(String aid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from admin where aid=?";
		return runner.query(sql, new BeanHandler<Admin>(Admin.class), aid);
	}

	/**
	 * 更新admin
	 * @param admin
	 * @throws SQLException 
	 */
	public void updateAdmin(Admin admin) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update admin set adminName=?,adminPassword=?,adminPrivilege=?,create_time=? where aid=?";
		runner.update(sql, 
				admin.getAdminName(),
				admin.getAdminPassword(),
				admin.getAdminPrivilege(),
				admin.getCreate_time(),
				admin.getAid());
		
	}

}
