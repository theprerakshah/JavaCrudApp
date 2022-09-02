package com.mypackage.CurdApp;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnector {
//	private static final String driverPath = "com.mysql.jdbc.Driver";
	private static final String databaseUrl = "jdbc:mysql://localhost:3306/MakeMyTrip";
	static final String userName = "root";
	private static final String password = "P@ssw0rd@123";
	static Connection conn;

	public static Connection getConnection() {

		if (conn == null) {
			try {
				conn = DriverManager.getConnection(databaseUrl, userName, password);
			} catch (Exception e) {
				System.out.println(e);
			}

			return conn;
		} else {
			return conn;
		}
	}
}
