package com.lihaogn.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lihaogn.domain.Admin;
import com.lihaogn.service.AdminService;

/**
 * Servlet implementation class AdminLoginServlet
 */
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 获取管理员名和密码
		String adminName = request.getParameter("admin_name");
		String adminPassword = request.getParameter("admin_password");
		// 将信息传给service层
		AdminService service=new AdminService();
		Admin admin=null;
		try {
			admin=service.login(adminName,adminPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 判断管理员是否成功登录
		if (admin!=null) { // 登录成功
			// 将admin对象存储到session中
			session.setAttribute("admin", admin);
			// 重定向到首页
			response.sendRedirect(request.getContextPath()+"/admin_index.jsp");
		}else {
			request.setAttribute("loginError", "用户名或密码错误！");
			request.getRequestDispatcher("/admin_login.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
