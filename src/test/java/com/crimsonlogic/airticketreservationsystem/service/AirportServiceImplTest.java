package com.crimsonlogic.airticketreservationsystem.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.crimsonlogic.airticketreservationsystem.entity.Airport;
import com.crimsonlogic.airticketreservationsystem.exception.RecordNotFoundException;
import com.crimsonlogic.airticketreservationsystem.repository.AirportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AirportServiceImplTest {

    @InjectMocks
    private AirportServiceImpl airportService;

    @Mock
    private AirportRepository airportRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testViewAllAirport() {
        List<Airport> airports = new ArrayList<>();
        when(airportRepository.findAll()).thenReturn(airports);

        Iterable<Airport> result = airportService.viewAllAirport();

        assertEquals(airports, result);
    }

    @Test
    public void testViewAirportExisting() {
        String airportCode = "EXIST";
        Airport airport = new Airport();
        when(airportRepository.findById(airportCode)).thenReturn(Optional.of(airport));

        Airport result = airportService.viewAirport(airportCode);

        assertEquals(airport, result);
    }

    @Test
    public void testViewAirportNonExisting() {
        String airportCode = "NONEXIST";
        when(airportRepository.findById(airportCode)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> airportService.viewAirport(airportCode));
    }

    @Test
    public void testModifyAirportExisting() {
        String airportCode = "MODIFY";
        Airport airport = new Airport();
        airport.setAirportCode(airportCode);
        when(airportRepository.findById(airportCode)).thenReturn(Optional.of(airport));

        Airport result = airportService.modifyAirport(airport);

        assertEquals(airport, result);
        verify(airportRepository, times(1)).save(airport);
    }

    @Test
    public void testModifyAirportNonExisting() {
        String airportCode = "NONEXISTING";
        Airport airport = new Airport();
        airport.setAirportCode(airportCode);
        when(airportRepository.findById(airportCode)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> airportService.modifyAirport(airport));
    }

    @Test
    public void testRemoveAirportExisting() {
        String airportCode = "REMOVE";
        Airport airport = new Airport();
        airport.setAirportCode(airportCode);
        when(airportRepository.findById(airportCode)).thenReturn(Optional.of(airport));

        String result = airportService.removeAirport(airportCode);

        assertEquals("Airport removed", result);
        verify(airportRepository, times(1)).deleteById(airportCode);
    }

    @Test
    public void testRemoveAirportNonExisting() {
        String airportCode = "NONEXISTING";
        when(airportRepository.findById(airportCode)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> airportService.removeAirport(airportCode));
    }
}
