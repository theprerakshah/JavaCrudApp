//package com.mypackage.CurdApp;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class Query {
//	public static String insertUser = "insert into User (UserName,UserContact,UserAddress,UserEmail,UserPassword)values(?,?,?,?,?)";
//	public static String tripDetails = "SELECT * FROM MakeMyTrip.TripInfo";
//	public static String BookingDetails = "SELECT * FROM MakeMyTrip.BookingDetails";
//	public static String retrive = "select * from User";
//	public static String placeorder = "insert into BookingDetails (BookingDetailsId,TripPlace,BookingDetailsPerson,BookingDetailsPayment,UserName) values(?,?,?,?,?)";
//	public static String deleteorder = "DELETE FROM BookingDetails WHERE BookingDetailsInfo= ?";
//	public static String updateorder = "UPDATE BookingDetails SET BookingDetailsId = ?, BookingDetailsPerson = ?, BookingDetailsPayment=? ,TripPlace=? WHERE BookingDetailsInfo =?";
//	public static String updatepassword = "UPDATE User SET  UserPassword=? WHERE UserName =?";
//
//	public static PreparedStatement preInsertUser = null;
//	public static PreparedStatement preretrive = null;
//	public static PreparedStatement pretripDetails = null;
//	public static PreparedStatement preBookingDetails = null;
//	public static PreparedStatement prePlaceorder = null;
//	public static PreparedStatement predeleteorder = null;
//	public static PreparedStatement preupdateorder = null;
//	public static PreparedStatement preupdatepassword = null;
//
//	Query(Connection con) throws SQLException {
//		preInsertUser = con.prepareStatement(insertUser);
//		preretrive = con.prepareStatement(insertUser);
//		pretripDetails = con.prepareStatement(insertUser);
//		preBookingDetails = con.prepareStatement(insertUser);
//		prePlaceorder = con.prepareStatement(insertUser);
//		predeleteorder = con.prepareStatement(insertUser);
//		preupdateorder = con.prepareStatement(insertUser);
//		preupdatepassword = con.prepareStatement(insertUser);
//
//	}
//
////	public static String userDetails(String username) {
////		return "Select * from User where UserName=\""+username+"\"";
////	}
//
//}
