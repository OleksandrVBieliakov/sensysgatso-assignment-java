package com.sensysgatso.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event implements BaseEntity {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;
    @Column(name = "event_date")
    private LocalDateTime eventDate;
    @Column(name = "event_type")
    private String eventType;
    @Column(name = "license_plate")
    private String licensePlate;
    private Double speed;
    @Column(name = "speed_limit")
    private Double limit;
    private String unity;
    private Boolean processed = false;
}
