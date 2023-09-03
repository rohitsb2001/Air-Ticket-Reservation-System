package com.crimsonlogic.airticketreservationsystem.controller;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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

 

import com.crimsonlogic.airticketreservationsystem.entity.Booking;
import com.crimsonlogic.airticketreservationsystem.service.BookingService;

 

public class BookingControllerTest {

 

    @Mock
    private BookingService bookingService;

 

    @InjectMocks
    private BookingController bookingController;

 

    private MockMvc mockMvc;

 

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
    }

 

    @Test
    public void testAddBooking() throws Exception {
        Booking booking = new Booking();

 

        mockMvc.perform(post("/booking/createBooking")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

 

    @Test
    public void testReadAllBookings() throws Exception {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking());
        given(bookingService.displayAllBooking()).willReturn(bookings);

 

        mockMvc.perform(get("/booking/readAllBooking"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(bookings.size()));
    }

 

    @Test
    public void testUpdateBooking() throws Exception {
        Booking booking = new Booking();

 

        mockMvc.perform(put("/booking/updateBooking")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

 

  

 

    @Test
    public void testDeleteBooking() throws Exception {
        BigInteger bookingId = BigInteger.valueOf(1);

 

        mockMvc.perform(delete("/booking/deleteBooking/{id}", bookingId))
                .andExpect(status().isOk());
    }
}