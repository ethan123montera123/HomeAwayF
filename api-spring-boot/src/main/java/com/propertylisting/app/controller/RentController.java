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
import com.propertylisting.app.model.Rent;
import com.propertylisting.app.service.RentService;

@RestController
@RequestMapping("/api/rents")
@CrossOrigin(origins = "http://localhost:4200") // Replace with your Angular app's URL
public class RentController {
    @Autowired
    private RentService rentService;


    @GetMapping("")
    public Response<List<Rent>> getAllBookings() {
        try {
            return new Response<>(true, null, rentService.getAllRents());
        } catch (IllegalStateException ex) {
            return new Response<>(true, ex.getMessage(), null);
        }
    }
    
    @PostMapping
    public Response<Rent> addRent(@RequestBody Rent rent) {
        try {

            // Validate if request body is null
            if (rent == null) {
                throw new IllegalStateException("Request body cannot be null.");
            }

            // Validate if rentStartDate is null
            if (rent.getRentStartDate() == null) {
                throw new IllegalStateException("Rent start date is required.");
            }

            // Validate if rentStartDate is in the past
            if (rent.getRentStartDate().isBefore(LocalDate.now())) {
                throw new IllegalStateException("Rent start date cannot be in the past.");
            }

            // Validate if bookingId is null or invalid
            if (rent.getBookingId() == null || rent.getBookingId() <= 0) {
                throw new IllegalStateException("Booking ID is required and must be greater than zero.");
            }

            return new Response<>(true, "Rent created successfully", rentService.addRent(rent));
        } catch (IllegalStateException ex) {
            return new Response<>(true, ex.getMessage(), null);
        }
    }

    @DeleteMapping("/end/{id}")
    public Response<Rent> endRent(@PathVariable Long id) {
        try {
            return new Response<>(true, "End rent deleted successfully.", rentService.endRent(id));
        } catch (IllegalStateException ex) {
            return new Response<>(true, ex.getMessage(), null);
        }
    }
}
