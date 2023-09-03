package com.crimsonlogic.airticketreservationsystem.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crimsonlogic.airticketreservationsystem.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, BigInteger> {

}
//