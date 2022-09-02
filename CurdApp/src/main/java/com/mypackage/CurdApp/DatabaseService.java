package com.mypackage.CurdApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseService
{

	public static String			insertUser			= "insert into User (UserName,UserContact,UserAddress,UserEmail,UserPassword)values(?,?,?,?,?)";
	public static String			tripDetails			= "SELECT * FROM MakeMyTrip.TripInfo";
	public static String			BookingDetails		= "SELECT * FROM MakeMyTrip.BookingDetails";
	public static String			retrive				= "select * from User";
	public static String			placeorder			= "insert into BookingDetails (BookingDetailsId,TripPlace,BookingDetailsPerson,BookingDetailsPayment,UserName) values(?,?,?,?,?)";
	public static String			deleteorder			= "DELETE FROM BookingDetails WHERE BookingDetailsInfo= ?";
	public static String			updateorder			= "UPDATE BookingDetails SET BookingDetailsId = ?, BookingDetailsPerson = ?, BookingDetailsPayment=? ,TripPlace=? WHERE BookingDetailsInfo =?";
	public static String			updatepassword		= "UPDATE User SET  UserPassword=? WHERE UserName =?";

	public static PreparedStatement	preInsertUser		= null;
	public static PreparedStatement	preretrive			= null;
	public static PreparedStatement	pretripDetails		= null;
	public static PreparedStatement	preBookingDetails	= null;
	public static PreparedStatement	prePlaceorder		= null;
	public static PreparedStatement	predeleteorder		= null;
	public static PreparedStatement	preupdateorder		= null;
	public static PreparedStatement	preupdatepassword	= null;

	DatabaseService(Connection con)
	{
		try
		{
			preInsertUser = con.prepareStatement(insertUser);
			preretrive = con.prepareStatement(retrive);
			pretripDetails = con.prepareStatement(tripDetails);
			preBookingDetails = con.prepareStatement(BookingDetails);
			prePlaceorder = con.prepareStatement(placeorder);
			predeleteorder = con.prepareStatement(deleteorder);
			preupdateorder = con.prepareStatement(updateorder);
			preupdatepassword = con.prepareStatement(updatepassword);

		}
		catch(Exception e)
		{
			System.out.println(e + "error in object");
		}
	}

	public void insertuser(User user) throws SQLException
	{
		try
		{
			preInsertUser.setString(1, user.getUserName());
			preInsertUser.setString(2, user.getUserContact());
			preInsertUser.setString(3, user.getUserAddress());
			preInsertUser.setString(4, user.getUserEmail());
			preInsertUser.setString(5, user.getUserPassword());
			int row = preInsertUser.executeUpdate();
			if(row > 0)
			{
				System.out.println("###########User Added  successfully#############");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

	public boolean crosscheck(String name, String password) throws SQLException
	{
		boolean boom = false;
		try
		{
			ResultSet rs = preretrive.executeQuery(retrive);
			while(rs.next())
			{

				if(name.equals(rs.getString("UserName")) && password.equals(rs.getString("UserPassword")))
				{
					boom = true;
				}

			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

		return boom;
	}

	public void updatepassword(String username, String password) throws SQLException
	{
		try
		{

			preupdatepassword.setString(1, password);
			preupdatepassword.setString(2, username);

			int row = preupdatepassword.executeUpdate();
			if(row > 0)
			{
				System.out.println("#########your password has been updated successfully########");
			}

		}
		catch(Exception e)
		{
			System.out.println(e);
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

	public void getTripDetails() throws SQLException
	{
		try
		{

			ResultSet rs = pretripDetails.executeQuery();
			System.out.print("TripId");
			System.out.print("\t\t TripPlace");
			System.out.print("\t\t TripDuration");
			System.out.println("\t\t TripPrice");
			while(rs.next())
			{
				System.out.printf("%-16s ", rs.getString("TripId"));

				System.out.printf("%-25s ", rs.getString("TripPlace"));
				System.out.printf("%-21s ", rs.getString("TripDuration"));
				System.out.printf("%s \n", rs.getString("TripPrice"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public int placeorder(BookingDetails details, String username) throws SQLException
	{
		int price = 0;
		String place = "";
		try
		{

			ResultSet rs = pretripDetails.executeQuery();
			while(rs.next())
			{
				//				System.out.println(rs.getString("TripId"));
				if(Integer.parseInt(rs.getString("TripId")) == details.getBookingDetailsId())
				{

					//					System.out.println("id matches here");
					place = rs.getString("TripPlace");
					price = Integer.parseInt(rs.getString("TripPrice"));
				}
			}

			int total = details.getBookingDetailsPerson();
			int ftotal = total * price;

			prePlaceorder.setInt(1, details.getBookingDetailsId());
			prePlaceorder.setString(2, place);
			prePlaceorder.setInt(3, details.getBookingDetailsPerson());
			prePlaceorder.setInt(4, ftotal);
			prePlaceorder.setString(5, username);

			int row = prePlaceorder.executeUpdate();
			if(row > 0)
			{
				System.out.println("\t \t ######Your Order has been placed Successfully######");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return 0;
	}

	public void getbookingdetails(String username) throws SQLException
	{
		try
		{

			ResultSet rs = preBookingDetails.executeQuery(BookingDetails);

			System.out.print("BookingId");
			System.out.print("\t\t PlaceId");
			System.out.print("\t\t Place");
			System.out.print("\t\t \t No. of person");
			System.out.println("\t\t Total Cost:");
			boolean flag = true;
			while(rs.next())
			{

				if(rs.getString("UserName").equals(username))
				{
					flag = false;
					System.out.printf("%-26s ", rs.getString("BookingDetailsInfo"));

					System.out.printf("%-22s ", rs.getString("BookingDetailsId"));
					System.out.printf("%-27s ", rs.getString("TripPlace"));
					System.out.printf("%-22s ", rs.getString("BookingDetailsPerson"));
					System.out.printf("%s \n", rs.getString("BookingDetailsPayment"));
				}
			}
			if(flag)
			{
				System.out.println("\t\t  ##### You have not Booked any trip ####");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public void deletemyorder(int id) throws SQLException
	{

		try
		{
			predeleteorder.setInt(1, id);
			int row = predeleteorder.executeUpdate();
			if(row > 0)
			{
				System.out.println("\t \t ####your order is deleted####");
			}
			else
			{
				System.out.println("\t\t !!!!!no such order!!!!!!");
			}

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public void updateOrder(int id, int updatetripid, int updateperson) throws SQLException
	{
		int pay = 0;
		try
		{

			//			ResultSet rs = st.executeQuery(Query.BookingDetails());
			//			while (rs.next()) {
			//				if (rs.getString("BookinDetailInfo").equals(id)) {
			//
			//					pay = Integer.parseInt(rs.getString("BookingDetailsPayment"));
			//				}
			//			}
			String place = "";
			ResultSet rs = pretripDetails.executeQuery();
			while(rs.next())
			{
				if(Integer.parseInt(rs.getString("TripId")) == updatetripid)
				{
					pay = Integer.parseInt(rs.getString("TripPrice"));
					place = rs.getString("TripPlace");

				}

			}
			pay = pay * updateperson;
			preupdateorder.setInt(1, updatetripid);
			preupdateorder.setInt(2, updateperson);
			preupdateorder.setInt(3, pay);
			preupdateorder.setString(4, place);

			preupdateorder.setInt(5, id);

			int row = preupdateorder.executeUpdate();
			if(row > 0)
			{
				System.out.println("\t\t #############your order has been updated successfully#########");
			}
			else
			{
				System.out.println("\t\t !!!!!!!!!!!!!!!!there is no such !!!!!!!!!!!!!!!!!");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}