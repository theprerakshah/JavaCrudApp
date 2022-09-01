package com.mypackage.CurdApp;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;

public class Query {
//	public static PreparedStatement pre = null;
//
//	public Query(Connection con) throws SQLException {
//		con.prepareStatement(tripDetails());
//	}

	public static String insertUser = "insert into User (UserName,UserContact,UserAddress,UserEmail,UserPassword)values(?,?,?,?,?)";

	public static String retrive() {
		return "select * from User";
	}

	public static String tripDetails() {
		return "SELECT * FROM MakeMyTrip.TripInfo";
	}

	public static String BookingDetails() {
		return "SELECT * FROM MakeMyTrip.BookingDetails";

	}

	public static String placeorder() {
		return "insert into BookingDetails (BookingDetailsId,TripPlace,BookingDetailsPerson,BookingDetailsPayment,UserName) values(?,?,?,?,?)";
	}

	public static String deleteorder() {
		return "DELETE FROM BookingDetails WHERE BookingDetailsInfo= ?";
	}

	public static String updateorder() {
		return "UPDATE BookingDetails SET BookingDetailsId = ?, BookingDetailsPerson = ?, BookingDetailsPayment=? ,TripPlace=? WHERE BookingDetailsInfo =?";
	}

	public static String updatepassword() {
		return "UPDATE User SET  UserPassword=? WHERE UserName =?";
	}

//	public static String userDetails(String username) {
//		return "Select * from User where UserName=\""+username+"\"";
//	}

}
