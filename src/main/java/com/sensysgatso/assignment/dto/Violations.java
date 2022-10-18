package com.sensysgatso.assignment.dto;

import com.sensysgatso.assignment.model.Violation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Violations {
    private List<Violation> violations;
}
