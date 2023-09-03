package com.crimsonlogic.airticketreservationsystem.controller;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

 

import java.util.ArrayList;
import java.util.List;

 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.crimsonlogic.airticketreservationsystem.entity.Airport;
import com.crimsonlogic.airticketreservationsystem.exception.RecordAlreadyPresentException;
import com.crimsonlogic.airticketreservationsystem.exception.RecordNotFoundException;
import com.crimsonlogic.airticketreservationsystem.service.AirportService;

 

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

 

@ExtendWith(MockitoExtension.class)
public class AirportControllerTest {

 

    @Mock
    private AirportService airportService;

 

    @InjectMocks
    private AirportController airportController;

 

    private Airport airport;

 

    @BeforeEach
    public void setUp() {
        airport = new Airport();
        airport.setAirportCode("ABC");
        // Set other properties of the airport
    }

 

    @Test
    public void testViewAirportSuccess() {
        given(airportService.viewAirport("ABC")).willReturn(airport);

 

        Airport result = airportController.viewAirport("ABC");

 

        assertThat(result).isEqualTo(airport);
    }

 

    @Test
    public void testViewAllAirport() {
        List<Airport> airportList = new ArrayList<>();
        airportList.add(airport);

 

        given(airportService.viewAllAirport()).willReturn(airportList);

 

        Iterable<Airport> result = airportController.viewAllAirport();

 

        assertThat(result).isEqualTo(airportList);
    }

 

    @Test
    public void testAddAirportSuccess() {
        airportController.addAirport(airport);

 

        verify(airportService, times(1)).addAirport(airport);
    }

 

    @Test
    public void testAddAirportAlreadyPresent() {
        given(airportService.addAirport(airport)).willThrow(new RecordAlreadyPresentException("Airport already present"));

 

        assertThatThrownBy(() -> airportController.addAirport(airport))
            .isInstanceOf(RecordAlreadyPresentException.class)
            .hasMessageContaining("Airport already present");
    }

 

    @Test
    public void testModifyAirportSuccess() {
        airportController.modifyAirport(airport);

 

        verify(airportService, times(1)).modifyAirport(airport);
    }

 

    @Test
    public void testModifyAirportNotFound() {
        given(airportService.modifyAirport(airport)).willThrow(new RecordNotFoundException("Airport not found"));

 

        assertThatThrownBy(() -> airportController.modifyAirport(airport))
            .isInstanceOf(RecordNotFoundException.class)
            .hasMessageContaining("Airport not found");
    }

 

    @Test
    public void testRemoveAirport() {
        airportController.removeAirport("ABC");

 

        verify(airportService, times(1)).removeAirport("ABC");
    }
}