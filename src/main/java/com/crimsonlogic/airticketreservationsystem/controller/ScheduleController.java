package com.crimsonlogic.airticketreservationsystem.controller;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.airticketreservationsystem.entity.Schedule;
import com.crimsonlogic.airticketreservationsystem.service.ScheduleService;
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/createSchedule")
    public void createSchedule(@RequestBody Schedule schedule) {
        scheduleService.saveSchedule(schedule);
    }

    @GetMapping("/findScheduleById/{scheduleId}")
    public Optional<Schedule> findScheduleById(@PathVariable("scheduleId") BigInteger scheduleId) {
        return scheduleService.findScheduleById(scheduleId);
    }

    @GetMapping("/findAllSchedules")
    public List<Schedule> findAllSchedules() {
        return scheduleService.findAllSchedules();
    }

    @DeleteMapping("/deleteSchedule/{scheduleId}")
    public void deleteSchedule(@PathVariable("scheduleId") BigInteger scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
    }
}
