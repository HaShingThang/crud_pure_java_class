// com.employee.utils.DBUtil.java

package com.employee.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String URL = "jdbc:postgresql://localhost:5432/mtm_employees";
	private static final String USER = "postgres";
	private static final String PASSWORD = "root";

	private static Connection connection;

	private DBUtil() {
	}

	public static Connection getConnection() {
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Connected to the database successfully!");
			} catch (SQLException e) {
				System.err.println("Failed to connect to the database!");
				e.printStackTrace();
				throw new RuntimeException("Database connection error", e);
			}
		}
		return connection;
	}

	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
				System.out.println("Database connection closed.");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Error closing database connection", e);
			} finally {
				connection = null;
			}
		}
	}
}
