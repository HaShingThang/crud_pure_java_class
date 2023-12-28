package com.employee.dao;

import com.employee.model.EmployeeModel;
import com.employee.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
	private static final String INSERT_EMPLOYEE = "INSERT INTO employees(name, position, salary) VALUES (?, ?, ?)";
	private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM employees";
	private static final String SELECT_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE id=?";
	private static final String UPDATE_EMPLOYEE = "UPDATE employees SET name=?, position=?, salary=? WHERE id=?";
	private static final String DELETE_EMPLOYEE = "DELETE FROM employees WHERE id=?";

	/// crate new employee
	public int insertEmployee(EmployeeModel employee) {
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE,
						Statement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getposition());
			preparedStatement.setDouble(3, employee.getSalary());

			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected == 1) {
				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
				if (generatedKeys.next()) {
					int generatedId = generatedKeys.getInt(1);
					employee.setId(generatedId);
				}
			}

			return rowsAffected;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error inserting employee", e);
		}
	}

	/// get all employees
	public List<EmployeeModel> getAllEmployees() {
		List<EmployeeModel> employees = new ArrayList<>();

		try (Connection connection = DBUtil.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SELECT_ALL_EMPLOYEES)) {

			while (resultSet.next()) {
				EmployeeModel employee = new EmployeeModel();
				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setposition(resultSet.getString("position"));
				employee.setSalary(resultSet.getDouble("salary"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error getting all employees", e);
		}

		return employees;
	}

	/// get employee by ID
	public EmployeeModel getEmployeeById(int employeeId) {
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID)) {

			preparedStatement.setInt(1, employeeId);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					EmployeeModel employee = new EmployeeModel();
					employee.setId(resultSet.getInt("id"));
					employee.setName(resultSet.getString("name"));
					employee.setposition(resultSet.getString("position"));
					employee.setSalary(resultSet.getDouble("salary"));
					return employee;
				}
			}

			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error getting employee by ID", e);
		}
	}

	/// update employees
	public int updateEmployee(EmployeeModel employee) {
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE)) {

			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getposition());
			preparedStatement.setDouble(3, employee.getSalary());
			preparedStatement.setInt(4, employee.getId());

			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error updating employee", e);
		}
	}

	/// delete employee
	public int deleteEmployee(int employeeId) {
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE)) {

			preparedStatement.setInt(1, employeeId);

			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error deleting employee", e);
		}
	}
}
