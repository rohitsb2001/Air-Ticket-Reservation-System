package com.crimsonlogic.airticketreservationsystem.service;

import com.crimsonlogic.airticketreservationsystem.entity.Booking;
import com.crimsonlogic.airticketreservationsystem.exception.RecordNotFoundException;
import com.crimsonlogic.airticketreservationsystem.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceImplTest {

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Test
    public void testCreateBookingSuccess() {
        Booking booking = new Booking();
        booking.setBookingId(BigInteger.valueOf(1L));

        when(bookingRepository.findById(booking.getBookingId())).thenReturn(Optional.empty());

        ResponseEntity<Booking> result = bookingService.createBooking(booking);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testCreateBookingAlreadyPresent() {
        Booking booking = new Booking();
        booking.setBookingId(BigInteger.valueOf(1L));

        when(bookingRepository.findById(booking.getBookingId())).thenReturn(Optional.of(booking));

        ResponseEntity<Booking> result = bookingService.createBooking(booking);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void testUpdateBookingSuccess() {
        Booking booking = new Booking();
        booking.setBookingId(BigInteger.valueOf(1L));

        when(bookingRepository.findById(booking.getBookingId())).thenReturn(Optional.of(booking));

        Booking updatedBooking = bookingService.updateBooking(booking);

        assertEquals(booking, updatedBooking);
    }

    @Test
    public void testUpdateBookingNonExisting() {
        Booking booking = new Booking();
        booking.setBookingId(BigInteger.valueOf(1L));

        when(bookingRepository.findById(booking.getBookingId())).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> bookingService.updateBooking(booking));
    }

    @Test
    public void testDeleteBookingSuccess() {
        BigInteger bookingId = BigInteger.valueOf(1L);

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(new Booking()));

        String result = bookingService.deleteBooking(bookingId);

        assertEquals("Booking Deleted!!", result);
    }

    @Test
    public void testDeleteBookingNonExisting() {
        BigInteger bookingId = BigInteger.valueOf(1L);

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> bookingService.deleteBooking(bookingId));
    }

    @Test
    public void testDisplayAllBooking() {
        List<Booking> bookings = new ArrayList<>();
        when(bookingRepository.findAll()).thenReturn(bookings);

        Iterable<Booking> result = bookingService.displayAllBooking();

        assertIterableEquals(bookings, result);
    }

    @Test
    public void testFindBookingByIdSuccess() {
        BigInteger bookingId = BigInteger.valueOf(1L);
        Booking booking = new Booking();
        booking.setBookingId(bookingId);

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));

        ResponseEntity<?> result = bookingService.findBookingById(bookingId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(booking, result.getBody());
    }

    @Test
    public void testFindBookingByIdNonExisting() {
        BigInteger bookingId = BigInteger.valueOf(1L);

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.empty());

        ResponseEntity<?> result = bookingService.findBookingById(bookingId);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

}
