package com.neosoft.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer {
	@Id
	private Integer id;
	private String name;
	
	@OneToMany(targetEntity = Product.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "cust_ref",referencedColumnName = "id")
	private List<Product> products;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	public Customer(Integer id, String name, List<Product> products) {
		super();
		this.id = id;
		this.name = name;
		this.products = products;
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
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", products=" + products + "]";
	}
	

}
