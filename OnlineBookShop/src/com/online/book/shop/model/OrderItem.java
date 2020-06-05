package com.online.book.shop.model;

public class OrderItem {

	private int orderItemId;
	private int quantity;
	private Book book;
	private Order orderId;
	
	public OrderItem() {	}

	public OrderItem(int quantity, Book book, Order orderId) {
		super();
		this.quantity = quantity;
		this.book = book;
		this.orderId = orderId;
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Order getorderId() {
		return orderId;
	}

	public void setorderId(Order orderId) {
		this.orderId = orderId;
	}
	
	
}
