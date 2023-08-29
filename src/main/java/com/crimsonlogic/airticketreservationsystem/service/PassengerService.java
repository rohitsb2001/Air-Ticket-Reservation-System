package com.crimsonlogic.airticketreservationsystem.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.crimsonlogic.airticketreservationsystem.entity.Passenger;

public interface PassengerService {

    public Passenger savePassenger(Passenger passenger);

    public Optional<Passenger> findPassengerById(BigInteger pnrNumber);

    public List<Passenger> findAllPassengers();

    public void deletePassenger(BigInteger pnrNumber);

}
