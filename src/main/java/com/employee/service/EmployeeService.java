package com.employee.service;

import com.employee.dao.EmployeeDAO;
import com.employee.model.EmployeeModel;

import java.util.List;

public class EmployeeService {
	private final EmployeeDAO employeeDAO;

	public EmployeeService() {
		this.employeeDAO = new EmployeeDAO();
	}

	/// create
	public int createEmployee(EmployeeModel employee) {
		return employeeDAO.insertEmployee(employee);
	}

	/// get all
	public List<EmployeeModel> getAllEmployees() {
		return employeeDAO.getAllEmployees();
	}

	/// get by ID
	public EmployeeModel getEmployeeById(int employeeId) {
		return employeeDAO.getEmployeeById(employeeId);
	}

	/// update
	public int updateEmployee(EmployeeModel employee) {
		return employeeDAO.updateEmployee(employee);
	}

	/// delete
	public int deleteEmployee(int employeeId) {
		return employeeDAO.deleteEmployee(employeeId);
	}
}
