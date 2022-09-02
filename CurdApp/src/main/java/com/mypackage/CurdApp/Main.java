package com.mypackage.CurdApp;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;
//import java.util.regex.Pattern;

public class Main
{

	static Connection				con	= MyConnector.getConnection();
	public static DatabaseService	dbs	= new DatabaseService(con);
	public static Scanner			sc	= new Scanner(System.in);

	public static boolean validate(int n)
	{

		String valid = "^[0-9]*\\b";

		return Integer.toString(n).matches(valid);
	}

	public static ArrayList<String> signIn()
	{
		ArrayList<String> mylist = new ArrayList<>();
		String name = "";
		String email = "";
		String contact = "";
		String password = "";
		String address = "";

		while(!validation.nameValidate(name))
		{
			System.out.print("Enter name:");
			name = sc.next();
		}
		mylist.add(name);
		while(!validation.emailValidate(email))
		{

			System.out.print("Enter Email");
			email = sc.next();
		}
		mylist.add(email);
		while(!validation.contactValidate(contact))
		{
			System.out.print("Enter Contact ");
			contact = sc.next();
		}
		mylist.add(contact);
		System.out.print("Enter Address ");
		address = sc.next();
		mylist.add(address);
		while(!validation.passwordValidate(password))
		{
			System.out.print("Enter Password ");
			password = sc.next();
		}
		mylist.add(password);

		return mylist;
	}

	public static String option()
	{

		String choice = "";
		System.out.println("");
		System.out.println("_______________________________________________________________________________________________________");
		System.out.println("\t 1 to show me trip details");
		System.out.println("\t 2 to Book a Trip");
		System.out.println("\t 3 to show you order");
		System.out.println("\t 4 to delete your order");
		System.out.println("\t 5 to update your order");

		System.out.println("\t -1 to exit");
		System.out.println("\t enter you choice");
		choice = sc.next();
		System.out.println("-----------------------------------------------------------------------------------------------");
		return choice;
	}

	public static void welcome(String username) throws Exception
	{

		boolean boom = true;

		System.out.println("\t Welcome " + username + " to my trip booking website");

		while(boom)
		{
			try
			{

				switch(option())
				{

					case "1":
						try
						{
							dbs.getTripDetails();
						}
						catch(Exception e)
						{
							System.out.println("error occured here");
						}

						break;
					case "2":
						System.out.print("Enter TripId where you want to go: ");
						int tripid = sc.nextInt();
						System.out.print("Enter number of persons: ");
						int person = sc.nextInt();

						dbs.placeorder(new BookingDetails(tripid, person), username);

						break;
					case "3":
						dbs.getbookingdetails(username);
						break;
					case "4":
						dbs.getbookingdetails(username);
						System.out.print("enter the bookingId of you order which you want to delete: ");
						int id = sc.nextInt();
						if(validate(id))
						{
							dbs.deletemyorder(id);
						}
						else
						{
							System.out.println("enter proper details!!!");
						}

						break;
					case "5":
						System.out.print("enter order id which you want to update:-->");

						int updateid = sc.nextInt();

						System.out.print("enter new trip id where you want to go:-->");
						int updatetripid = sc.nextInt();
						System.out.print("enter the number of person you want to go-->");
						int updateperson = sc.nextInt();
						if(validate(updateid) && validate(updatetripid) && validate(updateperson))
						{
							dbs.updateOrder(updateid, updatetripid, updateperson);
						}
						else
						{
							System.out.println("\t\t !!!!!!!enter proper details!!!!!");
						}
						break;
					case "-1":
						boom = false;
						System.out.println("############ Thanku for using our booking service############");
						break;

					default:
						System.out.println("\t\t !!!!!!!!Enter proper option!!!!!!!!");
						break;
				}
			}
			catch(Exception e)
			{
				System.out.println("\t\t !!!!!!!!!!invalid input!!!!!!!!!!");
			}
		}
	}

	public static void main(String[] args) throws Exception
	{

		System.out.println("\t \t <----------------welcome to my application--------------->");
		new DatabaseService(con);
		String choice = "";
		boolean flag = true;

		while(flag)
		{
			System.out.println("_________________________________________________________________________________________");
			System.out.println("\t enter you choice");
			System.out.println("\t 1 to SignUp");
			System.out.println("\t 2 to SignIn");
			System.out.println("\t 3 to change password");
			//			System.out.println("\t 4 to show user details:");
			System.out.println("\t -1 to exit");
			System.out.println("");

			choice = sc.next();

			switch(choice)
			{
				case "1":
					ArrayList<String> mylist = signIn();

					dbs.insertuser(new User(mylist.get(0), mylist.get(1), mylist.get(2), mylist.get(3), mylist.get(4)));

					break;
				case "2":
					System.out.print("Enter your name: ");
					String username = sc.next();
					System.out.print("Enter password: ");
					String userpassword = sc.next();
					if(dbs.crosscheck(username, userpassword))
					{

						welcome(username);
					}
					else
					{
						System.out.println("\t\t !!!!!!!!!!!!!UserName or Password is Incorrect!!!!!!!!!!!!!!!! ");
					}

					break;
				case "3 ":
					System.out.println("Enter your Username: ");
					String username1 = sc.next();
					System.out.println("Enter your old Password: ");
					String password1 = sc.next();
					if(dbs.crosscheck(username1, password1))
					{
						String pass = "";
						while(!validation.passwordValidate(pass))
						{
							System.out.println("Enter new password:");
							pass = sc.next();
						}
						dbs.updatepassword(username1, pass);
					}
					else
					{
						System.out.println("\t\t !!!!!!!!!!!!!!!UserName or Password is Incorrect!!!!!!!!!!!!!!!! ");
					}
					break;
				//			case 4 + "":
				//				System.out.println("Enter your Username: ");
				//				String username2 = sc.next();
				//				System.out.println("Enter your old Password: ");
				//				String password2 = sc.next();
				//				if (dbs.crosscheck(username2, password2)) {
				//					System.out.println("Hello " + username2 + " your details are: ");
				//					dbs.showUserDetais(username2);
				//
				//				} else {
				//					System.out.println("UserName or Password is Incorrect ");
				//				}

				case "-1":
					System.out.println("$$$$$$$ Exited successfully$$$$$$$$");
					flag = false;
					break;
				default:
					System.out.println("");
					System.out.println("\t\t !!!!!!!!!!!!!!!!Enter proper option!!!!!!!!!!!!!!!!");
					break;
			}
		}
		sc.close();
		con.close();
	}
}
