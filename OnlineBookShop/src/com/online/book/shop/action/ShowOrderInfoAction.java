package com.online.book.shop.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.online.book.shop.delegate.OrderDelegate;
import com.online.book.shop.to.OrderTO;
import com.online.book.shop.to.UserTO;

public class ShowOrderInfoAction {

	public String getOrderInfo(HttpServletRequest req, HttpServletResponse res){
		
		String page=req.getParameter("page");
		String orderId =req.getParameter("orderId");
		System.out.println(orderId+"*************");
		OrderTO oto = OrderDelegate.getOrderByOrderId(orderId);
		System.out.println("ORDET_TO : "+oto);
		req.setAttribute("ORDER_TO", oto);
		List orderList = null;
		if(page.equals("userOrderStatusDef.jsp")){
			Object obj = req.getSession().getAttribute("USER_TO");
			UserTO uto =(UserTO)obj;
			orderList = OrderDelegate.getOrderByUserId(uto.getUserId());
		}else{
			orderList = OrderDelegate.getAllOrderInfo();
		}
		if(orderList != null){
			req.setAttribute("ORDER_FOUND", orderList);
		}
		return page;
	}
}
