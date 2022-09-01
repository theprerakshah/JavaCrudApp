package com.mypackage.CurdApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseService extends Query {
	MyConnector connector = new MyConnector();

	public void insertuser(User user) throws SQLException {
		try (Connection con = MyConnector.getConnection(); PreparedStatement pre = con.prepareStatement(insertUser);) {
			pre.setString(1, user.getUserName());
			pre.setString(2, user.getUserContact());
			pre.setString(3, user.getUserAddress());
			pre.setString(4, user.getUserEmail());
			pre.setString(5, user.getUserPassword());
			int row = pre.executeUpdate();
			if (row > 0) {
				System.out.println("###########User Added  successfully#############");
			}
		}

	}

	public boolean crosscheck(String name, String password) throws SQLException {
		boolean boom = false;
		try (Connection con = MyConnector.getConnection(); Statement st = con.createStatement();) {
			ResultSet rs = st.executeQuery(Query.retrive());
			while (rs.next()) {

				if (name.equals(rs.getString("UserName")) && password.equals(rs.getString("UserPassword"))) {
					boom = true;
				}

			}
		}

		return boom;
	}

	public void updatepassword(String username, String password) throws SQLException {
		try (Connection con = MyConnector.getConnection();
				PreparedStatement pre = con.prepareStatement(Query.updatepassword());) {

			pre.setString(1, password);
			pre.setString(2, username);

			int row = pre.executeUpdate();
			if (row > 0) {
				System.out.println("#########your password has been updated successfully########");
			}

		}
	}

//	public void showUserDetais(String username) throws SQLException {
//		try (Connection con = MyConnector.getConnection(); Statement st = con.createStatement();) {
//
//			ResultSet rs = st.executeQuery(Query.userDetails(username));
//			System.out.print("UserId");
//			System.out.print("\t\t UserName");
//			System.out.print("\t\t UserContact");
//			System.out.print("\t\t UserAddress");
//			System.out.println("\t\t UserEmail");
//			while (rs.next()) {
//				System.out.printf("%-16s ", rs.getString("UserId"));
//
//				System.out.printf("%-25s ", rs.getString("UserName"));
//				System.out.printf("%-21s ", rs.getString("UserContact"));
//				System.out.printf("%s-21 ", rs.getString("UserAddress"));
//				System.out.printf("%s \n", rs.getString("UserEmail"));
//			}
//		}
//	}

	public void getTripDetails() throws SQLException {
		try (Connection con = MyConnector.getConnection(); Statement st = con.createStatement();) {

			ResultSet rs = st.executeQuery(Query.tripDetails());
			System.out.print("TripId");
			System.out.print("\t\t TripPlace");
			System.out.print("\t\t TripDuration");
			System.out.println("\t\t TripPrice");
			while (rs.next()) {
				System.out.printf("%-16s ", rs.getString("TripId"));

				System.out.printf("%-25s ", rs.getString("TripPlace"));
				System.out.printf("%-21s ", rs.getString("TripDuration"));
				System.out.printf("%s \n", rs.getString("TripPrice"));
			}
		}
	}

	public int placeorder(BookingDetails details, String username) throws SQLException {
		int price = 0;
		String place = "";
		try (Connection con = MyConnector.getConnection();
				PreparedStatement pre = con.prepareStatement(Query.placeorder());
				Statement st = con.createStatement();) {

			ResultSet rs = st.executeQuery(Query.tripDetails());
			while (rs.next()) {
//				System.out.println(rs.getString("TripId"));
				if (Integer.parseInt(rs.getString("TripId")) == details.getBookingDetailsId()) {

//					System.out.println("id matches here");
					place = rs.getString("TripPlace");
					price = Integer.parseInt(rs.getString("TripPrice"));
				}
			}

			int total = details.getBookingDetailsPerson();
			int ftotal = total * price;

			pre.setInt(1, details.getBookingDetailsId());
			pre.setString(2, place);
			pre.setInt(3, details.getBookingDetailsPerson());
			pre.setInt(4, ftotal);
			pre.setString(5, username);

			int row = pre.executeUpdate();
			if (row > 0) {
				System.out.println("\t \t ######Your Order has been placed Successfully######");
			}
		}
		return 0;
	}

	public void getbookingdetails(String username) throws SQLException {
		try (Connection con = MyConnector.getConnection(); Statement st = con.createStatement();) {

			ResultSet rs = st.executeQuery(Query.BookingDetails());

			System.out.print("BookingId");
			System.out.print("\t\t PlaceId");
			System.out.print("\t\t Place");
			System.out.print("\t\t \t No. of person");
			System.out.println("\t\t Total Cost:");
			boolean flag = true;
			while (rs.next()) {

				if (rs.getString("UserName").equals(username)) {
					flag = false;
					System.out.printf("%-26s ", rs.getString("BookingDetailsInfo"));

					System.out.printf("%-22s ", rs.getString("BookingDetailsId"));
					System.out.printf("%-27s ", rs.getString("TripPlace"));
					System.out.printf("%-22s ", rs.getString("BookingDetailsPerson"));
					System.out.printf("%s \n", rs.getString("BookingDetailsPayment"));
				}
			}
			if (flag) {
				System.out.println("\t\t  ##### You have not Booked any trip ####");
			}
		}
	}

	public void deletemyorder(int id) throws SQLException {

		try (Connection con = MyConnector.getConnection();
				PreparedStatement pre = con.prepareStatement(Query.deleteorder());) {
			pre.setInt(1, id);
			int row = pre.executeUpdate();
			if (row > 0) {
				System.out.println("\t \t ####your order is deleted####");
			} else {
				System.out.println("\t\t !!!!!no such order!!!!!!");
			}

		}
	}

	public void updateOrder(int id, int updatetripid, int updateperson) throws SQLException {
		int pay = 0;
		try (Connection con = MyConnector.getConnection();
				PreparedStatement pre = con.prepareStatement(Query.updateorder());
				Statement st = con.createStatement();) {

//			ResultSet rs = st.executeQuery(Query.BookingDetails());
//			while (rs.next()) {
//				if (rs.getString("BookinDetailInfo").equals(id)) {
//
//					pay = Integer.parseInt(rs.getString("BookingDetailsPayment"));
//				}
//			}
			String place = "";
			ResultSet rs = st.executeQuery(Query.tripDetails());
			while (rs.next()) {
				if (Integer.parseInt(rs.getString("TripId")) == updatetripid) {
					pay = Integer.parseInt(rs.getString("TripPrice"));
					place = rs.getString("TripPlace");
					System.out.println(place);
				}

			}
			pay = pay * updateperson;
			pre.setInt(1, updatetripid);
			pre.setInt(2, updateperson);
			pre.setInt(3, pay);
			pre.setString(4, place);

			pre.setInt(5, id);

			int row = pre.executeUpdate();
			if (row > 0) {
				System.out.println("\t\t #############your order has been updated successfully#########");
			} else {
				System.out.println("\t\t !!!!!!!!!!!!!!!!there is no such !!!!!!!!!!!!!!!!!");
			}
		}
	}

}