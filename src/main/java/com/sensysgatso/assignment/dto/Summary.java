package com.sensysgatso.assignment.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Summary {
    private Integer count;
    private BigDecimal fine;
}

