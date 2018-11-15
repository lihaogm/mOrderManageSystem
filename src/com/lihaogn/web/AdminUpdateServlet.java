package com.lihaogn.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.lihaogn.domain.Admin;
import com.lihaogn.service.AdminService;

/**
 * Servlet implementation class AdminUpdateServlet
 */
public class AdminUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		// 1 获取数据
		Map<String, String[]> properties = request.getParameterMap();
		// 2 封装数据
		Admin admin = new Admin();
		try {
			BeanUtils.populate(admin, properties);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 3 手动设置没有的数据
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String adminDate = dateFormat.format(new Date());
		admin.setCreate_time(adminDate);
		
//		System.out.println(admin);
		// 4 service层
		AdminService service = new AdminService();
		service.updateAdmin(admin);
		// 5 跳转页面
		response.sendRedirect(request.getContextPath()+"/adminList");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
