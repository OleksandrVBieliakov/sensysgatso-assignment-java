package com.sensysgatso.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ViolationsSummary {
    private Summary paid;
    private Summary unpaid;
}
