package com.lihaogn.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.lihaogn.domain.User;
import com.lihaogn.utils.DataSourceUtils;

public class UserDao {

	public List<User> listAllUser() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select wx_openid,nick_name,city,gender,"
				+ "DATE_FORMAT(u.gmt_create, '%Y-%m-%d %T') as gmt_create,"
				+ "DATE_FORMAT(u.gmt_modified, '%Y-%m-%d %T') as gmt_modified from user u";
		return runner.query(sql, new BeanListHandler<User>(User.class));
	}

}
