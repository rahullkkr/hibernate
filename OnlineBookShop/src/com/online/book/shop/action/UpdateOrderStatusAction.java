package com.online.book.shop.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.online.book.shop.delegate.OrderDelegate;

public class UpdateOrderStatusAction {

	public String updateOrderStatus(HttpServletRequest req, HttpServletResponse res){

		String page="allOrdersDef.jsp";
		String orderId =req.getParameter("orderId");
		String status = req.getParameter("status");
		OrderDelegate.updateStatus(orderId, status);
		List orderList = OrderDelegate.getAllOrderInfo();
		if(orderList != null ){
			req.setAttribute("ORDER_FOUND", orderList);
		}
		return page;
	}	
}
