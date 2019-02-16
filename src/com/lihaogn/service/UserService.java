package com.lihaogn.service;

import java.sql.SQLException;
import java.util.List;

import com.lihaogn.dao.UserDao;
import com.lihaogn.domain.User;

public class UserService {

	public List<User> listAllUser() {
		UserDao userDao = new UserDao();
		List<User> userList = null;
		try {
			userList = userDao.listAllUser();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

}
