package com.employee.controller;

import com.employee.model.EmployeeModel;
import com.employee.service.EmployeeService;

import java.util.List;
import java.util.Scanner;

public class EmployeeController {
	private final EmployeeService employeeService;

	public EmployeeController() {
		this.employeeService = new EmployeeService();
	}

	public void start() {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("1. Add Employee");
			System.out.println("2. View All Employees");
			System.out.println("3. View Employee by ID");
			System.out.println("4. Update Employee");
			System.out.println("5. Delete Employee");
			System.out.println("6. Exit");

			System.out.print("Enter your choice(1-5): ");
			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				addEmployee(scanner);
				break;
			case 2:
				viewAllEmployees();
				break;
			case 3:
				viewEmployeeById(scanner);
				break;
			case 4:
				updateEmployee(scanner);
				break;
			case 5:
				deleteEmployee(scanner);
				break;
			case 6:
				System.out.println("Exiting...");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice. Try again.");
			}
		}
	}

	/// create new employee
	private void addEmployee(Scanner scanner) {
		System.out.print("Enter employee name: ");
		String name = scanner.next();

		System.out.print("Enter employee position: ");
		String position = scanner.next();

		System.out.print("Enter employee salary: ");
		double salary = scanner.nextDouble();

		EmployeeModel employee = new EmployeeModel(name, position, salary);

		int rowsAffected = employeeService.createEmployee(employee);

		if (rowsAffected == 1) {
			System.out.println("Employee added successfully. ID: " + employee.getId());
		} else {
			System.out.println("Failed to add employee. Please try again.");
		}
	}

	/// get all employees
	private void viewAllEmployees() {
		List<EmployeeModel> employees = employeeService.getAllEmployees();

		if (employees.isEmpty()) {
			System.out.println("No employees found.");
		} else {
			System.out.println("Employee List:");
			for (EmployeeModel employee : employees) {
				System.out.println("ID: " + employee.getId() + ", Name: " + employee.getName() + ", Position: "
						+ employee.getposition() + ", Salary: " + employee.getSalary());
			}
		}
	}

	/// get employee by ID
	private void viewEmployeeById(Scanner scanner) {
		System.out.print("Enter employee ID to view: ");
		int employeeId = scanner.nextInt();

		EmployeeModel employee = employeeService.getEmployeeById(employeeId);

		if (employee != null) {
			System.out.println("Employee Details:");
			System.out.println("ID: " + employee.getId() + ", Name: " + employee.getName() + ", Position: "
					+ employee.getposition() + ", Salary: " + employee.getSalary());
		} else {
			System.out.println("Employee with ID " + employeeId + " not found.");
		}
	}

	/// update employee
	private void updateEmployee(Scanner scanner) {
		System.out.print("Enter employee ID to update: ");
		int employeeId = scanner.nextInt();

		System.out.print("Enter updated employee name: ");
		String name = scanner.next();

		System.out.print("Enter updated employee position: ");
		String position = scanner.next();

		System.out.print("Enter updated employee salary: ");
		double salary = scanner.nextDouble();

		EmployeeModel employee = new EmployeeModel(name, position, salary);
		employee.setId(employeeId);

		int rowsAffected = employeeService.updateEmployee(employee);

		if (rowsAffected == 1) {
			System.out.println("Employee updated successfully.");
		} else {
			System.out.println("Failed to update employee. Please check the employee ID.");
		}
	}

	/// delete employee
	private void deleteEmployee(Scanner scanner) {
		System.out.print("Enter employee ID to delete: ");
		int employeeId = scanner.nextInt();

		int rowsAffected = employeeService.deleteEmployee(employeeId);

		if (rowsAffected == 1) {
			System.out.println("Employee deleted successfully.");
		} else {
			System.out.println("Failed to delete employee. Please check the employee ID.");
		}
	}
}
