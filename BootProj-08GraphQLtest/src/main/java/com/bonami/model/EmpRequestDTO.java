package com.bonami.model;

public class EmpRequestDTO {
	
	String name;
	String dept;
	
	public EmpRequestDTO() {
		// TODO Auto-generated constructor stub
	}

	public EmpRequestDTO(String name, String dept) {
		super();
		this.name = name;
		this.dept = dept;
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
		return "EmpRequestDTO [name=" + name + ", dept=" + dept + "]";
	}
	
	

}
