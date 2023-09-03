package com.crimsonlogic.airticketreservationsystem.service;

import java.math.BigInteger;

import com.crimsonlogic.airticketreservationsystem.entity.ScheduledFlight;
import com.crimsonlogic.airticketreservationsystem.exception.RecordNotFoundException;
import com.crimsonlogic.airticketreservationsystem.exception.ScheduledFlightNotFoundException;




public interface ScheduledFlightService {
	public ScheduledFlight addScheduledFlight(ScheduledFlight scheduledFlight);

	public ScheduledFlight modifyScheduledFlight(ScheduledFlight scheduledFlight);

	public String removeScheduledFlight(BigInteger id) throws RecordNotFoundException;

	public Iterable<ScheduledFlight> viewAllScheduledFlights();

	public ScheduledFlight viewScheduledFlight(BigInteger id) throws ScheduledFlightNotFoundException;
	// boolean cancelBookings(BigInteger flightId) throws RecordNotFoundException;

}
//