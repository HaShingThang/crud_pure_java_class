// com.employee.schema.SchemaManager.java

package com.employee.schema;

import com.employee.utils.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateSchema {
	public static void createEmployeesTable() {
		try (Connection connection = DBUtil.getConnection(); Statement statement = connection.createStatement()) {

			String createTableSQL = "CREATE TABLE IF NOT EXISTS employees (" + "id SERIAL PRIMARY KEY,"
					+ "name VARCHAR(255) NOT NULL," + "position VARCHAR(255) NOT NULL,"
					+ "salary DOUBLE PRECISION NOT NULL" + ")";

			statement.executeUpdate(createTableSQL);

			System.out.println("Table 'employees' created successfully!");
		} catch (SQLException e) {
			System.err.println("Error creating 'employees' table!");
			e.printStackTrace();
			throw new RuntimeException("Table creation error", e);
		}
	}
}
