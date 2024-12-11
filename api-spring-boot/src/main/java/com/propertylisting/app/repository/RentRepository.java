package com.propertylisting.app.repository;

import com.propertylisting.app.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Rent, Long> {
}
