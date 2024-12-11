package com.propertylisting.app.repository;

import com.propertylisting.app.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
