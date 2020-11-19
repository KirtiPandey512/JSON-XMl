package com.books.entity;

public class Publisher{
	private String title;
	private String id;
	private long price;
	private Author[] authors;
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String title, String id, long price, Author[] authors) {
		super();
		this.title = title;
		this.id = id;
		this.price = price;
		this.authors = authors;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public Author[] getAuthors() {
		return authors;
	}
	public void setAuthors(Author[] authors) {
		this.authors = authors;
	}
	
}
