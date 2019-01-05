package com.lihaogn.web;

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

		String orderId=request.getParameter("orderId");
		String userName = request.getParameter("userName");
		String orderTotalPrice = request.getParameter("orderTotalPrice");
		System.out.println("orderId: "+orderId+"\r\n"+"userName: "+userName+"\r\n"+"orderTotalPrice: "+orderTotalPrice);
		
//		Gson gson=new Gson();
//		OrderInfoWX orderInfoX = gson.fromJson(orderInfo, OrderInfoWX.class);
//		System.out.println(orderInfoX);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
