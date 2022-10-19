package com.sensysgatso.assignment.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Summary {
    private Integer count;
    private BigDecimal fine;

    /**
     * used by {@link com.sensysgatso.assignment.repository.jpa.ViolationJpaRepository#findSummary}
     */
    public Summary(Long count, BigDecimal fine) {
        this.count = count.intValue();
        this.fine = fine == null ? BigDecimal.ZERO : fine;
    }
}

