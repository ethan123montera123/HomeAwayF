package com.propertylisting.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "properties", schema = "dbo")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private double pricePerNight;

    @Enumerated(EnumType.STRING)
    private PropertyType type;

    @Column(nullable = false)
    private Long ownerId;

    @Column(name = "bed_count", nullable = false)
    private Long bedCount;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;
}

enum PropertyType {
    APARTMENT, HOUSE, ROOM
}
