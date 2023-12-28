package com.employee.test;

import com.employee.schema.CreateSchema;

public class TestDBConnection {
	public static void main(String[] args) {
		try {

			CreateSchema.createEmployeesTable();

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}