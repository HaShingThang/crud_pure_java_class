package com.employee.test;

import com.employee.controller.EmployeeController;
import com.employee.utils.DBUtil;

public class TestEmployee {
	public static void main(String[] args) {
		try {
			EmployeeController employeeController = new EmployeeController();
			employeeController.start();
		} finally {
//			DBUtil.closeConnection();
		}
	}
}