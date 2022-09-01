package com.mypackage.CurdApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnector {
//	private static final String driverPath = "com.mysql.jdbc.Driver";
	private static final String databaseUrl = "jdbc:mysql://localhost:3306/MakeMyTrip";
	static final String userName = "root";
	private static final String password = "P@ssw0rd@123";

	public MyConnector() {
		try {

//			Class.forName(driverPath);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static Connection getConnection() throws SQLException {

		return DriverManager.getConnection(databaseUrl, userName, password);
	
	}
}
