package com.online.book.shop.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.online.book.shop.delegate.OrderDelegate;
import com.online.book.shop.delegate.UserDelegate;
import com.online.book.shop.to.UserTO;

public class ShowUserInfoAction {

	public String getUserInfo(HttpServletRequest req, HttpServletResponse res){
		
		String page="allOrdersDef.jsp";
		String userId = req.getParameter("userId");
		UserTO uto = UserDelegate.getUserInfoById(userId);
		req.setAttribute("USER_INFO", uto);
		List orderList = null;
		orderList = OrderDelegate.getAllOrderInfo();
		if(orderList != null){
			req.setAttribute("ORDER_FOUND", orderList);
		}
		return page;
	}	
}
