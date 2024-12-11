package com.propertylisting.app.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.propertylisting.app.dto.Response;
import com.propertylisting.app.model.Booking;
import com.propertylisting.app.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:4200") // Replace with your Angular app's URL
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("")
    public Response<List<Booking>> getAllBookings() {
        try {
            return new Response<>(true, null, bookingService.getAllBookings());
        } catch (IllegalStateException ex) {
            return new Response<>(true, ex.getMessage(), null);
        }
    }

    @PostMapping("")
    public Response<Booking> addBooking(@RequestBody Booking booking) {
        try {
            if (booking == null) {
                throw new IllegalStateException("Request body cannot be null.");
            }

            if (booking.getStartDate() == null) {
                throw new IllegalStateException("Rent start date is required.");
            }

            if (booking.getEndDate() == null) {
                throw new IllegalStateException("Rent end date is required.");
            }

            if (booking.getStartDate().isBefore(LocalDate.now())) {
                throw new IllegalStateException("Rent start date cannot be in the past.");
            }

            if (booking.getEndDate().isBefore(LocalDate.now())) {
                throw new IllegalStateException("Rent end date cannot be in the past.");
            }

            if (booking.getStartDate().isBefore(booking.getEndDate())) {
                throw new IllegalStateException("Rent start date is advance");
            }

            if (booking.getPropertyId() == null || booking.getPropertyId() <= 0) {
                throw new IllegalStateException("Property ID is required and must be greater than zero.");
            }

            if (booking.getClientId() == null || booking.getClientId() <= 0) {
                throw new IllegalStateException("Client ID is required and must be greater than zero.");
            }

            if (booking.getTotalPrice() <= 0) {
                throw new IllegalStateException("Total price be greater than zero.");
            }


            return new Response<>(true, "Booking created successfully", bookingService.addBooking(booking));
        } catch (IllegalStateException ex) {
            return new Response<>(true, ex.getMessage(), null);
        }
    }

    @DeleteMapping("/{id}")
    public Response<Boolean> deleteBooking(@PathVariable Long id) {
        try {
            return new Response<>(true, "Booking deleted successfully.", bookingService.deleteBooking(id));
        } catch (IllegalStateException ex) {
            return new Response<>(true, ex.getMessage(), null);
        }
    }
}
