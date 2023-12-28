package com.employee.model;

public class EmployeeModel {
	private int id;
	private String name;
	private String position;
	private double salary;

	public EmployeeModel() {

	}

	public EmployeeModel(String name, String position, double salary) {
		this.name = name;
		this.position = position;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getposition() {
		return position;
	}

	public void setposition(String position) {
		this.position = position;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
}
