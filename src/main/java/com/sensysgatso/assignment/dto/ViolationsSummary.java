package com.sensysgatso.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViolationsSummary {
    private Summary paid;
    private Summary unpaid;
}
