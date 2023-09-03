package com.crimsonlogic.airticketreservationsystem.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.airticketreservationsystem.entity.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, BigInteger> {

}
//