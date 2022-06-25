package com.poc.jms.model;

import java.util.Date;

public class Person {
	private int empId;
	private String firstname;
	private String lastName;
	private String email;
	private Date joinedDate;
	private long mobileNumber;
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	@Override
	public String toString() {
		return "Person [empId=" + empId + ", firstname=" + firstname + ", lastName=" + lastName + ", email=" + email
				+ ", joinedDate=" + joinedDate + "]";
	}
	public Person(int empId, String firstname, String lastName, String email, Date joinedDate) {
		super();
		this.empId = empId;
		this.firstname = firstname;
		this.lastName = lastName;
		this.email = email;
		this.joinedDate = joinedDate;
	}
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
