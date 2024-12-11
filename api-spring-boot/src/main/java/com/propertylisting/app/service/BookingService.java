package com.propertylisting.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.propertylisting.app.model.Booking;
import com.propertylisting.app.repository.BookingRepository;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking addBooking(Booking booking) {

        // Check if there are bookings associated with the property
        List<Booking> bookings = bookingRepository.findByPropertyId(booking.getPropertyId());
        if (!bookings.isEmpty()) {
            throw new IllegalStateException("Property already booked");
        }
        return bookingRepository.save(booking);
    }

    public boolean deleteBooking(Long id) {
        bookingRepository.deleteById(id);
        return true;
    }
}
