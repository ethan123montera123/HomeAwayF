package com.propertylisting.app.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "rents", schema = "dbo")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long bookingId;

    @Column(nullable = false)
    private LocalDate rentStartDate;

    @Column(nullable = false)
    private LocalDate rentEndDate;

    @Column(name = "is_end", nullable = false)
    private boolean isEnd = true; // Default value is false
}
