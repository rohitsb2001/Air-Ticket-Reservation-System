package com.crimsonlogic.airticketreservationsystem.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.crimsonlogic.airticketreservationsystem.entity.Schedule;

public interface ScheduleService {
	 public Schedule saveSchedule(Schedule schedule);
	 public Optional<Schedule> findScheduleById(BigInteger scheduleId) ;
	 public List<Schedule> findAllSchedules() ;
	 public void deleteSchedule(BigInteger scheduleId);


}
//