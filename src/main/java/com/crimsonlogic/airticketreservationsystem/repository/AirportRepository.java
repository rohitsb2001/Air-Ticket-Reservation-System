package com.crimsonlogic.airticketreservationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.airticketreservationsystem.entity.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, String> {

}
//