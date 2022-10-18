package com.sensysgatso.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Violation implements BaseEntity {
    private UUID id;
    private UUID eventId;
    private BigDecimal fine;
    private Boolean paid = false;
}
