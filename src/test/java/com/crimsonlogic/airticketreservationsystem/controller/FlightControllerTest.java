package com.crimsonlogic.airticketreservationsystem.controller;
import java.math.BigInteger;



import com.crimsonlogic.airticketreservationsystem.entity.Flight;
import com.crimsonlogic.airticketreservationsystem.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

 

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

 

@WebMvcTest(FlightController.class)
public class FlightControllerTest {

 

    @Autowired
    private MockMvc mockMvc;

 

    @MockBean
    private FlightService flightService;

 

    private Flight flight;

 

    @BeforeEach
    public void setUp() {
        flight = new Flight();
        flight.setFlightNo(BigInteger.valueOf(123L));
        // Set other properties of the flight
    }

 

    @Test
    public void testAddFlight() throws Exception {
        mockMvc.perform(post("/flight/addFlight")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"flightNo\": 123, \"otherProperty\": \"value\"}"))
                .andExpect(status().isOk());
    }

 

    @Test
    public void testViewAllFlight() throws Exception {
        mockMvc.perform(get("/flight/allFlight"))
                .andExpect(status().isOk());
    }

 

    @Test
    public void testViewFlight() throws Exception {
        BigInteger flightNo = flight.getFlightNo();
        given(flightService.viewFlight(flightNo)).willReturn(flight);

 

        mockMvc.perform(get("/flight/viewFlight/{id}", flightNo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flightNo").value(flightNo));
    }

 

    @Test
    public void testModifyFlight() throws Exception {
        mockMvc.perform(put("/flight/updateFlight")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"flightNo\": 123, \"otherProperty\": \"updatedValue\"}"))
                .andExpect(status().isOk());
    }

 

    @Test
    public void testRemoveFlight() throws Exception {
        BigInteger flightNo = flight.getFlightNo();
        mockMvc.perform(delete("/flight/deleteFlight/{id}", flightNo))
                .andExpect(status().isOk());
    }
}

 