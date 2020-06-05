package com.online.book.shop.dao;

import java.util.List;

import com.online.book.shop.to.OrderTO;

public interface OrderDAO {

	public String placeOrder(OrderTO oto, String ip);
	public List getOrderByUserId(String userId);
	public List getAllOrderInfo();
	public void updateStatus(String orderId, String status);
	public OrderTO getOrderByOrderId(String orderId);
	
}
