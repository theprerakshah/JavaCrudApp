package com.mypackage.CurdApp;

public class BookingDetails {

	private int BookingDetailsId;
	private int BookingDetailsPerson;

	public BookingDetails(int placeid, int person) {
		super();

		BookingDetailsId = placeid;
		BookingDetailsPerson = person;

	}

	public int getBookingDetailsId() {
		return BookingDetailsId;
	}

	public void setBookingDetailsId(int bookingDetailsId) {
		BookingDetailsId = bookingDetailsId;
	}

	public int getBookingDetailsPerson() {
		return BookingDetailsPerson;
	}

	public void setBookingDetailsPerson(int bookingDetailsPerson) {
		BookingDetailsPerson = bookingDetailsPerson;
	}

}
