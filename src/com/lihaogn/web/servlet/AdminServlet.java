package com.lihaogn.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;

import com.lihaogn.domain.Admin;
import com.lihaogn.service.AdminService;

/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 添加管理员
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		request.setCharacterEncoding("utf-8");

		// 1 获取数据
		Map<String, String[]> properties = request.getParameterMap();
		// 2 封装数据
		Admin admin = new Admin();
		try {
			BeanUtils.populate(admin, properties);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		// test
		System.out.println("AdminServlet addAdmin admin.name: "+admin.getAdminName());
		
		
		// 3 手动添加必要数据
		// id
		admin.setAid(UUID.randomUUID().toString());
		// 注册日期
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String adminDate = dateFormat.format(new Date());
		admin.setCreate_time(adminDate);

		AdminService service = new AdminService();
		service.addAdmin(admin);

		response.sendRedirect(request.getContextPath() + "/admin?method=getAdmin");
	}

	/**
	 * 检查管理员名是否存在
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void checkAdminName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 获得要校验的用户名
		String adminName = request.getParameter("adminName");
		System.out.println("adminname: " + adminName);

		AdminService adminService = new AdminService();
		boolean isExist = adminService.checkAdminName(adminName);

		response.getWriter().write("{\"isExist\":" + isExist + "}");
	}

	/**
	 * 显示管理员列表
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		AdminService adminService = new AdminService();
		List<Admin> listAdmin = adminService.getAllAdmin();
		
		// test
//		for (Admin admin : listAdmin) {
//			System.out.println("AdminServlet getAdmin admin.name: "+admin.getAdminName());
//		}
		
		request.setAttribute("allAdmins", listAdmin);
		request.getRequestDispatcher("/pages/admin_list.jsp").forward(request, response);
	}

	/**
	 * 删除一个管理员
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String aid = request.getParameter("aid");

		AdminService service = new AdminService();
		service.deleteAdminByAid(aid);
		response.sendRedirect(request.getContextPath() + "/admin?method=getAdmin");
	}

	/**
	 * 删除多个管理员
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteMultiAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String aids = request.getParameter("aids");

//		String[] split = aids.split(")");

		AdminService service = new AdminService();
		service.deleteMutilAdminByAid(aids);

		response.sendRedirect(request.getContextPath() + "/admin?method=getAdmin");
	}

	/**
	 * 编辑管理员准备数据
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void editAdminPrepared(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 获取要查询的aid，查询要更改的admin信息并回显
		String aid = request.getParameter("aid");
		AdminService service = new AdminService();
		Admin admin = service.getAdminByAid(aid);
		request.setAttribute("admin", admin);
		request.getRequestDispatcher("/pages/admin_edit.jsp").forward(request, response);
	}

	/**
	 * 编辑管理员信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void editAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		request.setCharacterEncoding("utf-8");

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

		// 3 手动设置没有的数�?
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String adminDate = dateFormat.format(new Date());
		admin.setCreate_time(adminDate);

//		System.out.println(admin);
		// 4 service�?
		AdminService service = new AdminService();
		service.updateAdmin(admin);
		// 5 跳转页面
		response.sendRedirect(request.getContextPath() + "/admin?method=getAdmin");
	}

	// ------------------------------------------------------------------------------
	// 管理员登录与登出
	// ------------------------------------------------------------------------------

	/**
	 * 管理员登录
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		// 获取管理员名和密码
		String adminName = request.getParameter("admin_name");
		
		// test
//		System.out.println("AdminServlet login -> admin.name: " + adminName);
//		System.out.println("AdminServlet login -> request.method"+request.getMethod());
		
		String adminPassword = request.getParameter("admin_password");
		// 将信息传给service层
		AdminService service = new AdminService();
		Admin admin = null;
		try {
			admin = service.login(adminName, adminPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 判断管理员是否成功登录
		if (admin != null) { // 登录成功
			// 将admin对象存储到session中
			session.setAttribute("admin", admin);
			// 判断用户是否选择自动登录
			String autoLoginFlag = request.getParameter("checkbox_autoLogin");
			if (autoLoginFlag != null) {
				// 对中文进行编码
				String adminNameCode = URLEncoder.encode(adminName, "UTF-8");

				Cookie cookieAdminName = new Cookie("cookieAdminName", adminNameCode);
				Cookie cookiePassword = new Cookie("cookiePassword", adminPassword);
				// 设置cookie的持久化时间
				cookieAdminName.setMaxAge(60 * 60 * 24);
				cookiePassword.setMaxAge(60 * 60 * 24);
				// 设置cookie的携带路径
				cookieAdminName.setPath(request.getContextPath());
				cookiePassword.setPath(request.getContextPath());
				// 发送cookie
				response.addCookie(cookieAdminName);
				response.addCookie(cookiePassword);
			}
			// 重定向到首页
			response.sendRedirect(request.getContextPath() + "/pages/index.jsp");
		} else {
			request.setAttribute("loginError", "用户名或密码错误！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	/**
	 * 管理员登出
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 用户注销
		HttpSession session = request.getSession();
		// 从session中删除admin
		session.removeAttribute("admin");
		// 从cookie中删除admin
		Cookie cookieAdminName = new Cookie("cookieAdminName", "");
		Cookie cookiePassword = new Cookie("cookiePassword", "");
		// 设置cookie的持久化时间
		cookieAdminName.setMaxAge(0);
		cookiePassword.setMaxAge(0);
		// 发送cookie
		response.addCookie(cookieAdminName);
		response.addCookie(cookiePassword);
		
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}

}
