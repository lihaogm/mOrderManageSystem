package com.lihaogn.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lihaogn.domain.OrderInfoWX;

/**
 * Servlet implementation class WXOrderAddServlet
 */
public class WXOrderAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderInfoWX orderInfoX;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		Gson gson=new Gson();
//		OrderInfoWX orderInfoX = gson.fromJson(orderInfo, OrderInfoWX.class);
//		System.out.println(orderInfoX);
		
		// 获取客户信息
		String customerInfoStr = request.getParameter("customerInfo");
		// 获取订单信息
		String orderInfoStr = request.getParameter("orderInfo");
		// test
		System.out.println("------------------------------------------------");
		System.out.println("WXOrderAddServlet -> customerInfo ："+customerInfoStr);
		System.out.println("WXOrderAddServlet -> orderInfo ："+orderInfoStr);
		System.out.println("------------------------------------------------");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
