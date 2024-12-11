package com.propertylisting.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.propertylisting.app.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByClientId(Long clientId);
    List<Booking> findByPropertyId(Long propertyId);
}
