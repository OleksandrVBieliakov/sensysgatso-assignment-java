package com.sensysgatso.assignment.controller;

import com.sensysgatso.assignment.dto.Violations;
import com.sensysgatso.assignment.dto.ViolationsSummary;
import com.sensysgatso.assignment.model.Violation;
import com.sensysgatso.assignment.service.ViolationService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/violation")
public class ViolationController {
    private final ViolationService violationService;

    public ViolationController(ViolationService violationService) {
        this.violationService = violationService;
    }

    @GetMapping("{id}")
    Violation getViolation(@PathVariable UUID id) {
        return violationService.getViolationById(id);
    }

    @GetMapping()
    Violations getAllViolations() {
        return new Violations(violationService.getAllViolations());
    }

    @GetMapping("/paid")
    Violations getPaidViolations() {
        return new Violations(violationService.getViolationsByPaid(true));
    }

    @GetMapping("/unpaid")
    Violations getUnpaidViolations() {
        return new Violations(violationService.getViolationsByPaid(false));
    }

    @PostMapping("/{id}/pay")
    void payViolation(@PathVariable UUID id) {
        violationService.payViolationById(id);
    }

    @GetMapping("/summary")
    ViolationsSummary getViolationsSummary() {
        return violationService.getViolationsSummary();
    }

}
