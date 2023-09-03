package com.crimsonlogic.airticketreservationsystem.service;

import com.crimsonlogic.airticketreservationsystem.entity.Passenger;
import com.crimsonlogic.airticketreservationsystem.repository.PassengerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PassengerServiceImplTest {

    @InjectMocks
    private PassengerServiceImpl passengerService;

    @Mock
    private PassengerRepository passengerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSavePassenger() {
        Passenger passenger = new Passenger();
        when(passengerRepository.save(passenger)).thenReturn(passenger);

        Passenger result = passengerService.savePassenger(passenger);

        assertEquals(passenger, result);
    }

    @Test
    public void testFindPassengerById() {
        BigInteger pnrNumber = BigInteger.valueOf(1L);
        Passenger passenger = new Passenger();
        when(passengerRepository.findById(pnrNumber)).thenReturn(Optional.of(passenger));

        Optional<Passenger> result = passengerService.findPassengerById(pnrNumber);

        assertEquals(Optional.of(passenger), result);
    }

    @Test
    public void testFindAllPassengers() {
        List<Passenger> passengers = new ArrayList<>();
        when(passengerRepository.findAll()).thenReturn(passengers);

        List<Passenger> result = passengerService.findAllPassengers();

        assertEquals(passengers, result);
    }

    @Test
    public void testDeletePassenger() {
        BigInteger pnrNumber = BigInteger.valueOf(1L);

        passengerService.deletePassenger(pnrNumber);

        verify(passengerRepository, times(1)).deleteById(pnrNumber);
    }
}
