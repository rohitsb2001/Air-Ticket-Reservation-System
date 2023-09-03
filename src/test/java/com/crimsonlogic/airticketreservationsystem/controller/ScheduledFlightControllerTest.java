package com.crimsonlogic.airticketreservationsystem.controller;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

 

import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.crimsonlogic.airticketreservationsystem.entity.Airport;
import com.crimsonlogic.airticketreservationsystem.entity.Flight;
import com.crimsonlogic.airticketreservationsystem.entity.Schedule;
import com.crimsonlogic.airticketreservationsystem.entity.ScheduledFlight;
import com.crimsonlogic.airticketreservationsystem.exception.RecordNotFoundException;
import com.crimsonlogic.airticketreservationsystem.exception.ScheduledFlightNotFoundException;
import com.crimsonlogic.airticketreservationsystem.service.AirportService;
import com.crimsonlogic.airticketreservationsystem.service.FlightService;
import com.crimsonlogic.airticketreservationsystem.service.ScheduledFlightService;

 

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

 

@ExtendWith(MockitoExtension.class)
public class ScheduledFlightControllerTest {

 

    @Mock
    private ScheduledFlightService scheduledFlightService;

 

    @Mock
    private AirportService airportService;

 

    @Mock
    private FlightService flightService;

 

    @InjectMocks
    private ScheduledFlightController scheduledFlightController;

 

    private ScheduledFlight scheduledFlight;

 

    @BeforeEach
    public void setUp() {
        scheduledFlight = new ScheduledFlight();
        scheduledFlight.setScheduleFlightId(BigInteger.valueOf(1L));
        // Set other properties of the scheduledFlight
    }

 

    @Test
    public void testAddSFSuccess() {
        Schedule schedule = new Schedule();
        schedule.setScheduleId(scheduledFlight.getScheduleFlightId());
        given(airportService.viewAirport(anyString())).willReturn(new Airport()); // Mock AirportService behavior
        given(flightService.viewFlight(scheduledFlight.getScheduleFlightId())).willReturn(new Flight()); // Mock FlightService behavior
        given(scheduledFlightService.addScheduledFlight(scheduledFlight)).willReturn(scheduledFlight);

 

        ResponseEntity<ScheduledFlight> result = scheduledFlightController.addSF(scheduledFlight, "src", "dst", "dept", "arr");

 

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

 

    @Test
    public void testAddSFFailure() {
        given(airportService.viewAirport(anyString())).willThrow(new RecordNotFoundException("Airport Not Found"));
        ResponseEntity<ScheduledFlight> result = scheduledFlightController.addSF(scheduledFlight, "src", "dst", "dept", "arr");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

 

    @Test
    public void testModifyScheduleFlight() {
        given(scheduledFlightService.modifyScheduledFlight(scheduledFlight)).willReturn(scheduledFlight);

 

        ResponseEntity<ScheduledFlight> result = scheduledFlightController.modifyScheduleFlight(scheduledFlight);

 

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(scheduledFlight);
    }

 

    @Test
    public void testModifyScheduleFlightNotModified() {
        given(scheduledFlightService.modifyScheduledFlight(scheduledFlight)).willReturn(null);

 

        ResponseEntity<ScheduledFlight> result = scheduledFlightController.modifyScheduleFlight(scheduledFlight);

 

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

 

    @Test
    public void testDeleteSF() {
        BigInteger flightId = scheduledFlight.getScheduleFlightId();
        given(scheduledFlightService.removeScheduledFlight(flightId)).willReturn("Flight Removed!!");

 

        String result = scheduledFlightController.deleteSF(flightId);

 

        assertThat(result).isEqualTo("Flight Removed!!");
    }

 

    @Test
    public void testViewSF() {
        BigInteger flightId = scheduledFlight.getScheduleFlightId();
        given(scheduledFlightService.viewScheduledFlight(flightId)).willReturn(scheduledFlight);

 

        ResponseEntity<ScheduledFlight> result = scheduledFlightController.viewSF(flightId);

 

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(scheduledFlight);
    }

 

    @Test
    public void testViewSFNotFound() {
        BigInteger flightId = scheduledFlight.getScheduleFlightId();
        given(scheduledFlightService.viewScheduledFlight(flightId)).willReturn(null);

 

        ResponseEntity<ScheduledFlight> result = scheduledFlightController.viewSF(flightId);

 

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

 

    @Test
    public void testViewAllSF() {
        given(scheduledFlightService.viewAllScheduledFlights()).willReturn(new ArrayList<>());

 

        Iterable<ScheduledFlight> result = scheduledFlightController.viewAllSF();

 

        assertThat(result).isEmpty();
    }
}