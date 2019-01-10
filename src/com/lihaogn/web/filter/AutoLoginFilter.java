package com.lihaogn.web.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lihaogn.domain.Admin;
import com.lihaogn.service.AdminService;

/**
 * Servlet Filter implementation class AutoLoginFilter
 */
public class AutoLoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AutoLoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		// session中存在的admin
		Admin admin = (Admin) session.getAttribute("admin");
		
		if (admin==null) {
			// 获得cookie中用户名和密码 进行登录的操作
			// 定义cookie_username
			String cookieAdminName = null;
			// 定义cookie_password
			String cookiePassword = null;
			// 获得cookie
			Cookie[] cookies = req.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					// 获得名字是cookie_username和cookie_password
					if ("cookieAdminName".equals(cookie.getName())) {
						cookieAdminName = cookie.getValue();
						// 恢复中文用户名
						cookieAdminName = URLDecoder.decode(cookieAdminName, "UTF-8");
					}
					if ("cookiePassword".equals(cookie.getName())) {
						cookiePassword = cookie.getValue();
					}
				}
			}

			// 判断username和password是否是null
			if (cookieAdminName != null && cookiePassword != null) {
				// 登录的代码
				AdminService service = new AdminService();
//				Admin adminOld = null;
				try {
					admin = service.login(cookieAdminName, cookiePassword);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				// 将登录的用户的user对象存到session中
				session.setAttribute("admin", admin);
			}
		}
		// 放行
		chain.doFilter(req, resp);
		
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	} 

}
