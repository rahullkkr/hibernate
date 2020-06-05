package com.online.book.shop.model;

import java.util.Set;

public class Book {

	private int bookId;
	private String bookName;
	private String author;
	private String publication;
	private String edition;
	private float cost;
	
	private Set<OrderItem> orderItems;
	
	public Book() {	}

	public Book(String bookName, String author, String publication,
			String edition, float cost) {
		super();
		this.bookName = bookName;
		this.author = author;
		this.publication = publication;
		this.edition = edition;
		this.cost = cost;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	 
}
