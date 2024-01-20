package com.neosoft.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Stud_Address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String city;
	private String pin;
	@ManyToOne(targetEntity = Student.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name ="stud_id",referencedColumnName = "sno")
	private Student stud;
	
	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Address(Integer id, String city, String pin, Student stud) {
		super();
		this.id = id;
		this.city = city;
		this.pin = pin;
		this.stud = stud;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public Student getStud() {
		return stud;
	}

	public void setStud(Student stud) {
		this.stud = stud;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", city=" + city + ", pin=" + pin + ", stud=" + stud + "]";
	}
	

}
