package com.lihaogn.web.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		// 获取管理员名和密码
		String adminName = request.getParameter("admin_name");
		System.out.println("login_adminName: "+adminName);
		System.out.println(request.getMethod());
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
		
//		System.out.println("login name: "+admin.getAdminName());
		
		
		// 判断管理员是否成功登录
		if (admin!=null) { // 登录成功
			// 将admin对象存储到session中
			session.setAttribute("admin", admin);
			// 判断用户是否选择自动登录
			String autoLoginFlag = request.getParameter("checkbox_autoLogin");
			if (autoLoginFlag!=null) {
				// 对中文进行编码
				String adminNameCode = URLEncoder.encode(adminName, "UTF-8");
				
				Cookie cookieAdminName = new Cookie("cookieAdminName",adminNameCode);
				Cookie cookiePassword = new Cookie("cookiePassword",adminPassword);
				//设置cookie的持久化时间
				cookieAdminName.setMaxAge(60*60*24);
				cookiePassword.setMaxAge(60*60*24);
				//设置cookie的携带路径
				cookieAdminName.setPath(request.getContextPath());
				cookiePassword.setPath(request.getContextPath());
				//发送cookie
				response.addCookie(cookieAdminName);
				response.addCookie(cookiePassword);
			}
			// 重定向到首页
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}else {
			request.setAttribute("loginError", "用户名或密码错误！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
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
