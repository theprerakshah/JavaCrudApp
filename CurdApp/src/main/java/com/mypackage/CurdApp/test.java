package com.mypackage.CurdApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.management.RuntimeErrorException;

public class test
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		String driverPath = "com.mysql.cj.jdbc.Driver";
		String databaseUrl = "jdbc:mysql://localhost:3306/MakeMyTrip";
		String userName = "root";
		String password = "P@ssw0rd@123";
		try
		{
			Class.forName(driverPath);
			Connection con = DriverManager.getConnection(databaseUrl, userName, password);
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM MakeMyTrip.User where UserId=101");
			while(rs.next())
			{
				System.out.println("hello");
				System.out.println(rs.getString("UserName"));
			}

			st.close();
			con.close();
		}
		catch(Exception e)
		{
			throw new RuntimeException("something went wrong " + e);
		}

	}
}
