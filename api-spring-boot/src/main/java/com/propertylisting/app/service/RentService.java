package com.propertylisting.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.propertylisting.app.model.Rent;
import com.propertylisting.app.repository.RentRepository;

@Service
public class RentService {
    @Autowired
    private RentRepository rentRepository;

    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }

    public Rent addRent(Rent rent) {
        return rentRepository.save(rent);
    }

    public Rent endRent(Long id) {
        try {
            // Check if the property exists
            Rent rent = rentRepository.findById(id).orElse(null);
            if (rent == null) {
                throw new IllegalStateException("Rent not found.");
            }

            // Mark the property as inactive instead of deleting
            rent.setEnd(true);
            rentRepository.save(rent);
            return rent;
        } catch (IllegalStateException | DataIntegrityViolationException ex) {
            // Rethrow exception with meaningful messages
            throw new IllegalStateException(ex.getMessage(), ex);
        }
    }
}
