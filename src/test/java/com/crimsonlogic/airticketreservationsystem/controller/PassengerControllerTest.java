package com.crimsonlogic.airticketreservationsystem.controller;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

 

import com.crimsonlogic.airticketreservationsystem.entity.Passenger;
import com.crimsonlogic.airticketreservationsystem.service.PassengerService;

 

public class PassengerControllerTest {

 

    @Mock
    private PassengerService passengerService;

 

    @InjectMocks
    private PassengerController passengerController;

 

    private MockMvc mockMvc;

 

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(passengerController).build();
    }

 

    @Test
    public void testCreatePassenger() throws Exception {
        Passenger passenger = new Passenger();
        passenger.setPnrNumber(BigInteger.valueOf(123));

 

        mockMvc.perform(post("/passenger/createPassenger")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"pnrNumber\": 123}"))
                .andExpect(status().isOk());
    }

 

    @Test
    public void testFindPassengerById() throws Exception {
        BigInteger pnrNumber = BigInteger.valueOf(123);
        Passenger passenger = new Passenger();
        passenger.setPnrNumber(pnrNumber);
        given(passengerService.findPassengerById(pnrNumber)).willReturn(Optional.of(passenger));

 

        mockMvc.perform(get("/passenger/findPassengerById/{pnrNumber}", pnrNumber))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pnrNumber").value(pnrNumber));
    }

 

    @Test
    public void testFindAllPassengers() throws Exception {
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(new Passenger());
        given(passengerService.findAllPassengers()).willReturn(passengers);

 

        mockMvc.perform(get("/passenger/findAllPassengers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(passengers.size()));
    }

 

    @Test
    public void testDeletePassenger() throws Exception {
        BigInteger pnrNumber = BigInteger.valueOf(123);

 

        mockMvc.perform(delete("/passenger/deletePassenger/{pnrNumber}", pnrNumber))
                .andExpect(status().isOk());
    }
}

