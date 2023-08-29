package com.crimsonlogic.airticketreservationsystem.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Booking {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookingId")
    private BigInteger bookingId;

    @NotNull
    @Column(name = "bookingDate")
    private String bookingDate;

    @NotNull
    @Min(1)
    @Column(name = "noOfPassengers")
    private int noOfPassengers;
	
public Booking() {
	// TODO Auto-generated constructor stub
}

public Booking(BigInteger bookingId, String bookingDate, int noOfPassengers) {
	super();
	this.bookingId = bookingId;
	this.bookingDate = bookingDate;
	this.noOfPassengers = noOfPassengers;
}

public BigInteger getBookingId() {
	return bookingId;
}

public void setBookingId(BigInteger bookingId) {
	this.bookingId = bookingId;
}

public String getBookingDate() {
	return bookingDate;
}

public void setBookingDate(String bookingDate) {
	this.bookingDate = bookingDate;
}

public int getNoOfPassengers() {
	return noOfPassengers;
}

public void setNoOfPassengers(int noOfPassengers) {
	this.noOfPassengers = noOfPassengers;
}

}
