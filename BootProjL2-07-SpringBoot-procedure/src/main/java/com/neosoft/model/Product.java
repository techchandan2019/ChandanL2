package com.neosoft.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class Product {

	@Id
	private Integer pid;
	private Double price;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(Integer pid, Double price) {
		super();
		this.pid = pid;
		this.price = price;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", price=" + price + "]";
	}
	
}
