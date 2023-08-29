package com.crimsonlogic.airticketreservationsystem.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
public class Passenger {

	@Id
	@Column(name = "pnrNumber")
	private BigInteger pnrNumber;
	
    @NotNull
	@Column(name = "passengerName")
	private String passengerName;
	
    @NotNull
	@Column(name = "passengerAge")
	private int passengerAge;
	
	@Column(name = "passengerUIN")
	private BigInteger passengerUIN;
	
	@Column(name = "luggage")
	@NotNull
	@DecimalMin(value = "0.0")
	private Double luggage;
	
	public BigInteger getPnrNumber() {
		return pnrNumber;
	}

	public void setPnrNumber(BigInteger pnrNumber) {
		this.pnrNumber = pnrNumber;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public int getPassengerAge() {
		return passengerAge;
	}

	public void setPassengerAge(int passengerAge) {
		this.passengerAge = passengerAge;
	}

	public BigInteger getPassengerUIN() {
		return passengerUIN;
	}

	public void setPassengerUIN(BigInteger passengerUIN) {
		this.passengerUIN = passengerUIN;
	}

	public Double getLuggage() {
		return luggage;
	}

	public void setLuggage(Double luggage) {
		this.luggage = luggage;
	}

	public Passenger(BigInteger pnrNumber, String passengerName, int passengerAge, BigInteger passengerUIN,
			Double luggage) {
		super();
		this.pnrNumber = pnrNumber;
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
		this.passengerUIN = passengerUIN;
		this.luggage = luggage;
	}

	public Passenger() {
		// TODO Auto-generated constructor stub
	}

}
