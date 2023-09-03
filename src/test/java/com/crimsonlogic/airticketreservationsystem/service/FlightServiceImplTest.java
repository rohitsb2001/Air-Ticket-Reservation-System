package com.crimsonlogic.airticketreservationsystem.service;

import com.crimsonlogic.airticketreservationsystem.entity.Flight;
import com.crimsonlogic.airticketreservationsystem.exception.RecordNotFoundException;
import com.crimsonlogic.airticketreservationsystem.repository.FlightRepository;
import com.crimsonlogic.airticketreservationsystem.service.FlightServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FlightServiceImplTest {

    @InjectMocks
    private FlightServiceImpl flightService;

    @Mock
    private FlightRepository flightRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddFlightSuccess() {
        Flight flight = new Flight();
        flight.setFlightNo(BigInteger.valueOf(1L));

        when(flightRepository.findById(flight.getFlightNo())).thenReturn(Optional.empty());
        when(flightRepository.save(flight)).thenReturn(flight);

        ResponseEntity<Flight> result = flightService.addFlight(flight);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testAddFlightAlreadyPresent() {
        Flight flight = new Flight();
        flight.setFlightNo(BigInteger.valueOf(1L));

        when(flightRepository.findById(flight.getFlightNo())).thenReturn(Optional.of(flight));

        ResponseEntity<Flight> result = flightService.addFlight(flight);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void testViewAllFlight() {
        List<Flight> flights = new ArrayList<>();

        when(flightRepository.findAll()).thenReturn(flights);

        Iterable<Flight> result = flightService.viewAllFlight();

        assertEquals(flights, result);
    }

    @Test
    public void testViewFlightExisting() {
        BigInteger flightNumber = BigInteger.valueOf(1L);
        Flight flight = new Flight();

        when(flightRepository.findById(flightNumber)).thenReturn(Optional.of(flight));

        Flight result = flightService.viewFlight(flightNumber);

        assertEquals(flight, result);
    }

    @Test
    public void testViewFlightNonExisting() {
        BigInteger flightNumber = BigInteger.valueOf(1L);

        when(flightRepository.findById(flightNumber)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> flightService.viewFlight(flightNumber));
    }

    @Test
    public void testModifyFlightExisting() {
        BigInteger flightNumber = BigInteger.valueOf(1L);
        Flight flight = new Flight();
        flight.setFlightNo(flightNumber);

        when(flightRepository.findById(flightNumber)).thenReturn(Optional.of(flight));
        when(flightRepository.save(flight)).thenReturn(flight);

        Flight result = flightService.modifyFlight(flight);

        assertEquals(flight, result);
    }

    @Test
    public void testModifyFlightNonExisting() {
        BigInteger flightNumber = BigInteger.valueOf(1L);
        Flight flight = new Flight();
        flight.setFlightNo(flightNumber);

        when(flightRepository.findById(flightNumber)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> flightService.modifyFlight(flight));
    }

    @Test
    public void testRemoveFlightExisting() {
        BigInteger flightNumber = BigInteger.valueOf(1L);
        Flight flight = new Flight();
        flight.setFlightNo(flightNumber);

        when(flightRepository.findById(flightNumber)).thenReturn(Optional.of(flight));

        String result = flightService.removeFlight(flightNumber);

        assertEquals("Flight removed!!", result);
        verify(flightRepository, times(1)).deleteById(flightNumber);
    }

    @Test
    public void testRemoveFlightNonExisting() {
        BigInteger flightNumber = BigInteger.valueOf(1L);

        when(flightRepository.findById(flightNumber)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> flightService.removeFlight(flightNumber));
    }
}
