package com.crimsonlogic.airticketreservationsystem.controller;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

 

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

 

import com.crimsonlogic.airticketreservationsystem.entity.Schedule;
import com.crimsonlogic.airticketreservationsystem.service.ScheduleService;

 

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

 

@ExtendWith(MockitoExtension.class)
public class ScheduleControllerTest {

 

    @Mock
    private ScheduleService scheduleService;

 

    @InjectMocks
    private ScheduleController scheduleController;

 

    private Schedule schedule;

 

    @BeforeEach
    public void setUp() {
        schedule = new Schedule();
        schedule.setScheduleId(BigInteger.valueOf(1L));
        // Set other properties of the schedule
    }

 

    @Test
    public void testCreateSchedule() {
        scheduleController.createSchedule(schedule);

 

        verify(scheduleService).saveSchedule(schedule);
    }

 

    @Test
    public void testFindScheduleById() {
        BigInteger scheduleId = schedule.getScheduleId();
        given(scheduleService.findScheduleById(scheduleId)).willReturn(Optional.of(schedule));

 

        Optional<Schedule> result = scheduleController.findScheduleById(scheduleId);

 

        assertThat(result).isNotEmpty();
        assertThat(result.get()).isEqualTo(schedule);
    }

 

    @Test
    public void testFindAllSchedules() {
        List<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(schedule);
        given(scheduleService.findAllSchedules()).willReturn(scheduleList);

 

        List<Schedule> result = scheduleController.findAllSchedules();

 

        assertThat(result).isEqualTo(scheduleList);
    }

 

    @Test
    public void testDeleteSchedule() {
        BigInteger scheduleId = schedule.getScheduleId();
        scheduleController.deleteSchedule(scheduleId);

 

        verify(scheduleService).deleteSchedule(scheduleId);
    }
}