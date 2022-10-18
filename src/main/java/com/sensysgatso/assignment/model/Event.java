package com.sensysgatso.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event implements BaseEntity {
    private UUID id;
    private LocalDateTime eventDate;
    private String eventType;
    private String licensePlate;
    private Double speed;
    private Double limit;
    private String unity;
    private Boolean processed = false;
}
