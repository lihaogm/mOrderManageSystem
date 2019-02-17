package com.lihaogn.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lihaogn.domain.User;
import com.lihaogn.service.UserService;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 获取用户列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void listUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UserService userService = new UserService();
		List<User> userList =userService.listAllUser(); 
		
		request.setAttribute("userList", userList);
		request.getRequestDispatcher("/member-list.jsp").forward(request, response);
	}
	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void UserLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
	}
	

}
