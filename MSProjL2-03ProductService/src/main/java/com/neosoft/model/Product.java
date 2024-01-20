package com.neosoft.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "Cust_Prod")
public class Product {
	
	@Id
	@SequenceGenerator(name = "gen1",initialValue = 1,allocationSize = 1,sequenceName = "SQ12")
	@GeneratedValue(generator = "gen1",strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String name;
	private Double price;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	public Product(Integer id, String name, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
		return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	
	

}
