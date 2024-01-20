package com.neosoft.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Emp")
public class Employee {
	
	@Id
	private Integer eno;
	private String ename;
	private Integer Sal;
	private Integer dept;
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	public Employee(Integer eno, String ename, Integer sal, Integer dept) {
		super();
		this.eno = eno;
		this.ename = ename;
		Sal = sal;
		this.dept = dept;
	}
	public Integer getEno() {
		return eno;
	}
	public void setEno(Integer eno) {
		this.eno = eno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public Integer getSal() {
		return Sal;
	}
	public void setSal(Integer sal) {
		Sal = sal;
	}
	public Integer getDept() {
		return dept;
	}
	public void setDept(Integer dept) {
		this.dept = dept;
	}
	@Override
	public String toString() {
		return "Employee [eno=" + eno + ", ename=" + ename + ", Sal=" + Sal + ", dept=" + dept + "]";
	}
}
