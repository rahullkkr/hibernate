package com.online.book.shop.model;

import java.util.Set;

public class User {

	private String userId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private long phone;
	private Login loginId;
	private Set<Order> order;
	
	public User() {	}

	public User(String firstName, String middleName, String lastName,
			String email, long phone) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
	}

	
	public Set<Order> getOrder() {
		return order;
	}

	public void setOrder(Set<Order> order) {
		this.order = order;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public Login getLoginId() {
		return loginId;
	}

	public void setLoginId(Login loginId) {
		this.loginId = loginId;
	}
	
}
