package com.lihaogn.service;

import java.sql.SQLException;
import java.util.List;

import com.lihaogn.dao.AdminDao;
import com.lihaogn.domain.Admin;

public class AdminService {

	/**
	 * 用户登录校验
	 * 
	 * @param adminName
	 * @param adminPassword
	 * @return
	 * @throws SQLException
	 */
	public Admin login(String adminName, String adminPassword) throws SQLException {
		AdminDao dao = new AdminDao();
		return dao.login(adminName, adminPassword);
	}

	/**
	 * 查询所有的admin
	 * 
	 * @return
	 */
	public List<Admin> getAllAdmin() {
		AdminDao dao = new AdminDao();
		List<Admin> allAdmins = null;
		try {
			allAdmins = dao.getAllAdmin();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allAdmins;
	}

	/**
	 * 添加一个admin
	 * 
	 * @param admin
	 */
	public void addAdmin(Admin admin) {
		AdminDao dao = new AdminDao();
		try {
			dao.addAdmin(admin);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 根据aid删除一个admin
	 * 
	 * @param aid
	 */
	public void deleteAdminByAid(String aid) {

		AdminDao dao = new AdminDao();
		try {
			dao.deleteAdminByAid(aid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 根据adi删除多个admin
	 * 
	 * @param aids
	 */
	public void deleteMutilAdminByAid(String aids) {

		AdminDao dao = new AdminDao();
		String[] split = aids.split("\\)");
		for (String aid : split) {
			try {
				dao.deleteAdminByAid(aid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据aid查询admin
	 * 
	 * @param aid
	 * @return
	 */
	public Admin getAdminByAid(String aid) {
		AdminDao dao = new AdminDao();
		Admin admin = null;
		try {
			admin = dao.getAdminByAid(aid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return admin;
	}

	/**
	 * 更新admin
	 * @param admin
	 */
	public void updateAdmin(Admin admin) {
		AdminDao dao = new AdminDao();
		try {
			dao.updateAdmin(admin);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
