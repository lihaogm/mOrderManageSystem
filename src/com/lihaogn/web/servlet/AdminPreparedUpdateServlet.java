package com.lihaogn.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lihaogn.domain.Admin;
import com.lihaogn.service.AdminService;

/**
 * Servlet implementation class AdminPreparedUpdateServlet
 */
public class AdminPreparedUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 获取要查询的aid，查询要更改的admin信息并回显
		String aid = request.getParameter("aid");
		AdminService service = new AdminService();
		Admin admin = service.getAdminByAid(aid);
		request.setAttribute("admin", admin);
		request.getRequestDispatcher("/admin_edit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
