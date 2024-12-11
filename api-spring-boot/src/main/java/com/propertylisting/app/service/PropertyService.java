package com.propertylisting.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.propertylisting.app.model.Booking;
import com.propertylisting.app.model.Property;
import com.propertylisting.app.repository.BookingRepository;
import com.propertylisting.app.repository.PropertyRepository;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id).orElse(null);
    }

    public Property addProperty(Property property) {
        return propertyRepository.save(property);
    }

    public Property updateProperty(Long id, Property propertyDetails) {
        Property property = propertyRepository.findById(id).orElse(null);
        if (property != null) {
            property.setTitle(propertyDetails.getTitle());
            property.setDescription(propertyDetails.getDescription());
            property.setAddress(propertyDetails.getAddress());
            property.setPricePerNight(propertyDetails.getPricePerNight());
            property.setType(propertyDetails.getType());
            property.setBedCount(propertyDetails.getBedCount());
            property.setType(propertyDetails.getType());
            return propertyRepository.save(property);
        }
        return null;
    }

    public boolean deleteProperty(Long id) {
        try {
            // Check if the property exists
            Property property = propertyRepository.findById(id).orElse(null);
            if (property == null) {
                throw new IllegalStateException("Property not found.");
            }

            // Check if there are bookings associated with the property
            List<Booking> bookings = bookingRepository.findByPropertyId(id);
            if (!bookings.isEmpty()) {
                throw new IllegalStateException("Cannot delete property as it has active bookings.");
            }

            // Mark the property as inactive instead of deleting
            property.setActive(false);
            propertyRepository.save(property);
            return true;

        } catch (IllegalStateException | DataIntegrityViolationException ex) {
            // Rethrow exception with meaningful messages
            throw new IllegalStateException(ex.getMessage(), ex);
        }
    }
}
