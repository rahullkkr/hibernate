package com.online.book.shop.model;

import java.util.Set;

public class Order {

	private String orderId;
	private String address;
	private String street;
	private String city;
	private String state;
	private String country;
	private String zip;
	private String cardNo;
	private String expDate;
	private float totalAmount;
	private int totalItem;
	private String  orderDate;
	private String status;
	private User userId;
	private Set<OrderItem> orderItems;
	
	public Order() {	}

	public Order(String address, String street, String city, String state,
			String country, String zip, String cardNo, String expDate,
			float totalAmount, int totalItem, String orderDate, String status,
			User userId, Set<OrderItem> orderItems ) {
		super();
		this.address = address;
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip = zip;
		this.cardNo = cardNo;
		this.expDate = expDate;
		this.totalAmount = totalAmount;
		this.totalItem = totalItem;
		this.orderDate = orderDate;
		this.status = status;
		this.userId = userId;
		this.orderItems = orderItems;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User user) {
		this.userId = user;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
}