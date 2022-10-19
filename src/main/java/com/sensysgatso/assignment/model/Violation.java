package com.sensysgatso.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Violation implements BaseEntity {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;
    @Column(name = "event_id", columnDefinition = "uuid")
    private UUID eventId;
    private BigDecimal fine;
    private Boolean paid = false;
}
