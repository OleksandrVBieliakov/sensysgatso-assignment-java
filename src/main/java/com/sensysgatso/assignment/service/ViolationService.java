package com.sensysgatso.assignment.service;

import com.sensysgatso.assignment.dto.ViolationsSummary;
import com.sensysgatso.assignment.model.Violation;

import java.util.List;
import java.util.UUID;

public interface ViolationService {
    Violation getViolationById(UUID id);

    List<Violation> getAllViolations();

    List<Violation> getViolationsByPaid(boolean paid);

    void payViolationById(UUID id);

    ViolationsSummary getViolationsSummary();
}
