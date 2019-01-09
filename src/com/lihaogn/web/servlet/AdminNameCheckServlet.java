package com.lihaogn.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lihaogn.service.AdminService;

/**
 * Servlet implementation class AdminNameCheckServlet
 */
public class AdminNameCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 获得要校验的用户名
		String adminName = request.getParameter("adminName");
		System.out.println("adminname: "+adminName);
		
		AdminService adminService = new AdminService();
		boolean isExist=adminService.checkAdminName(adminName);
		
		response.getWriter().write("{\"isExist\":"+isExist+"}");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
