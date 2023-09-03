package com.crimsonlogic.airticketreservationsystem.service;



import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
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

import com.crimsonlogic.airticketreservationsystem.entity.Schedule;
import com.crimsonlogic.airticketreservationsystem.repository.ScheduleRepository;

@ExtendWith(MockitoExtension.class)
public class ScheduleServiceImplTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    private Schedule schedule;

    @BeforeEach
    public void setUp() {
        schedule = new Schedule();
        schedule.setScheduleId(BigInteger.valueOf(1L));
        // Set other properties of the schedule
    }

    @Test
    public void testSaveSchedule() {
        given(scheduleRepository.save(schedule)).willReturn(schedule);

        Schedule result = scheduleService.saveSchedule(schedule);

        assertThat(result).isEqualTo(schedule);
    }

    @Test
    public void testFindScheduleById() {
        given(scheduleRepository.findById(schedule.getScheduleId())).willReturn(Optional.of(schedule));

        Optional<Schedule> result = scheduleService.findScheduleById(schedule.getScheduleId());

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(schedule);
    }

    @Test
    public void testFindAllSchedules() {
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(schedule);

        given(scheduleRepository.findAll()).willReturn(schedules);

        List<Schedule> result = scheduleService.findAllSchedules();

        assertThat(result).isEqualTo(schedules);
    }

    @Test
    public void testDeleteSchedule() {
        BigInteger scheduleId = schedule.getScheduleId();

        scheduleService.deleteSchedule(scheduleId);

        verify(scheduleRepository, times(1)).deleteById(scheduleId);
    }
}

