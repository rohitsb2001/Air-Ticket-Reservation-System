package com.crimsonlogic.airticketreservationsystem.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.airticketreservationsystem.entity.ScheduledFlight;


@Repository
public interface ScheduledFlightRepository extends JpaRepository<ScheduledFlight, BigInteger>{

}
