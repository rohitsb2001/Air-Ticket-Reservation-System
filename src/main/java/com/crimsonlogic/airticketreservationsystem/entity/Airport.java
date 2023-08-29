package com.crimsonlogic.airticketreservationsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

@Entity
public class Airport

{
    @Id
    @Column(name = "airportCode", unique = true)
    private String airportCode;

    @Column(name = "airportLocation")
    @NotNull
    @Size(max = 255)
    @Pattern(regexp = "^[a-zA-Z0-9\\-_\\.,]+$")
    private String airportLocation;

    @Column(name = "airportName")
    @NotNull
    @Size(max = 100)
    private String airportName;

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getAirportLocation() {
		return airportLocation;
	}

	public void setAirportLocation(String airportLocation) {
		this.airportLocation = airportLocation;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public Airport(String airportCode, String airportLocation, String airportName) {
		super();
		this.airportCode = airportCode;
		this.airportLocation = airportLocation;
		this.airportName = airportName;
	}

	public Airport() {
		// TODO Auto-generated constructor stub
	}
	

}
