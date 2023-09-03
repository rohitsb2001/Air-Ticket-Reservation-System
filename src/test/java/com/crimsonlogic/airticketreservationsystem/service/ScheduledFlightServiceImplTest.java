package com.crimsonlogic.airticketreservationsystem.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
import com.crimsonlogic.airticketreservationsystem.entity.ScheduledFlight;
import com.crimsonlogic.airticketreservationsystem.exception.ScheduledFlightNotFoundException;
import com.crimsonlogic.airticketreservationsystem.repository.ScheduleRepository;
import com.crimsonlogic.airticketreservationsystem.repository.ScheduledFlightRepository;

@ExtendWith(MockitoExtension.class)
public class ScheduledFlightServiceImplTest {

    @Mock
    private ScheduledFlightRepository scheduledFlightRepository;

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ScheduledFlightServiceImpl scheduledFlightService;

    private ScheduledFlight scheduledFlight;
    private Schedule schedule;

    @BeforeEach
    public void setUp() {
        schedule = new Schedule();
        scheduledFlight = new ScheduledFlight();
        scheduledFlight.setScheduleFlightId(BigInteger.valueOf(1L));
        scheduledFlight.setAvailableSeats(100);
        scheduledFlight.setSchedule(schedule);
    }

    @Test
    public void testAddScheduledFlight() {
        given(scheduledFlightRepository.save(scheduledFlight)).willReturn(scheduledFlight);

        ScheduledFlight result = scheduledFlightService.addScheduledFlight(scheduledFlight);

        assertThat(result).isEqualTo(scheduledFlight);
    }

    @Test
    public void testModifyScheduledFlightExisting() {
        given(scheduledFlightRepository.findById(scheduledFlight.getScheduleFlightId())).willReturn(Optional.of(scheduledFlight));
        given(scheduleRepository.findById(schedule.getScheduleId())).willReturn(Optional.of(schedule));
        given(scheduledFlightRepository.save(scheduledFlight)).willReturn(scheduledFlight);

        ScheduledFlight result = scheduledFlightService.modifyScheduledFlight(scheduledFlight);

        assertThat(result).isEqualTo(scheduledFlight);
    }

    @Test
    public void testRemoveScheduledFlightExisting() {
        given(scheduledFlightRepository.findById(scheduledFlight.getScheduleFlightId())).willReturn(Optional.of(scheduledFlight));

        String result = scheduledFlightService.removeScheduledFlight(scheduledFlight.getScheduleFlightId());

        assertThat(result).isEqualTo("Scheduled flight with ID " + scheduledFlight.getScheduleFlightId() + " is not found");
        verify(scheduledFlightRepository, times(1)).deleteById(scheduledFlight.getScheduleFlightId());
    }

    @Test
    public void testViewAllScheduledFlights() {
        List<ScheduledFlight> scheduledFlights = new ArrayList<>();
        scheduledFlights.add(scheduledFlight);

        given(scheduledFlightRepository.findAll()).willReturn(scheduledFlights);

        Iterable<ScheduledFlight> result = scheduledFlightService.viewAllScheduledFlights();

        assertThat(result).isEqualTo(scheduledFlights);
    }

    @Test
    public void testViewScheduledFlightExisting() {
        given(scheduledFlightRepository.findById(scheduledFlight.getScheduleFlightId())).willReturn(Optional.of(scheduledFlight));

        ScheduledFlight result = scheduledFlightService.viewScheduledFlight(scheduledFlight.getScheduleFlightId());

        assertThat(result).isEqualTo(scheduledFlight);
    }

    @Test
    public void testViewScheduledFlightNonExisting() {
        given(scheduledFlightRepository.findById(scheduledFlight.getScheduleFlightId())).willReturn(Optional.empty());

        assertThatThrownBy(() -> scheduledFlightService.viewScheduledFlight(scheduledFlight.getScheduleFlightId()))
                .isInstanceOf(ScheduledFlightNotFoundException.class);
    }
}
