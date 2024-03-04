package com.bonami.model;


public class Employee {

	private Integer id;
	private String name;
	private String dept;
	
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	public Employee(Integer id, String name, String dept) {
		super();
		this.id = id;
		this.name = name;
		this.dept = dept;
		
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
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", dept=" + dept + "]";
	}
	
	
	
}
