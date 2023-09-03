package com.crimsonlogic.airticketreservationsystem.service;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.airticketreservationsystem.entity.Schedule;
import com.crimsonlogic.airticketreservationsystem.repository.ScheduleRepository;


@Service
public class ScheduleServiceImpl implements ScheduleService{

@Autowired
	    private ScheduleRepository schRepo;

@Override
public Schedule saveSchedule(Schedule schedule) {
	// TODO Auto-generated method stub
	return schRepo.save(schedule);
}
//
@Override
public Optional<Schedule> findScheduleById(BigInteger scheduleId) {
	// TODO Auto-generated method stub
	return schRepo.findById(scheduleId);
}

@Override
public List<Schedule> findAllSchedules() {
	// TODO Auto-generated method stub
	return schRepo.findAll();
}

@Override
public void deleteSchedule(BigInteger scheduleId) {
	// TODO Auto-generated method stub
	schRepo.deleteById(scheduleId);
	
}


}
