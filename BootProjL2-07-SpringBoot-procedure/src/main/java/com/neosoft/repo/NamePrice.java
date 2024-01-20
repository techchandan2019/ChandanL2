package com.neosoft.repo;

public class NamePrice {
	
	private String name;
	private Double price;
	public NamePrice() {
		// TODO Auto-generated constructor stub
	}
	public NamePrice(String name, Double price) {
		super();
		this.name = name;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "NamePrice [name=" + name + ", price=" + price + "]";
	}
	

}
