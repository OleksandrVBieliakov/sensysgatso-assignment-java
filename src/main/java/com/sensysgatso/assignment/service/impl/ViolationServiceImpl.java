package com.sensysgatso.assignment.service.impl;

import com.sensysgatso.assignment.dto.Summary;
import com.sensysgatso.assignment.dto.ViolationsSummary;
import com.sensysgatso.assignment.exception.EntityNotFoundException;
import com.sensysgatso.assignment.model.Violation;
import com.sensysgatso.assignment.repository.ViolationRepository;
import com.sensysgatso.assignment.service.ViolationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class ViolationServiceImpl implements ViolationService {

    private final ViolationRepository violationRepository;

    public ViolationServiceImpl(ViolationRepository violationRepository) {
        this.violationRepository = violationRepository;
    }

    private final Summary zeroSummary = new Summary(0, BigDecimal.ZERO);

    @Override
    public Violation getViolationById(UUID id) {
        return violationRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find violation with ID " + id));
    }

    @Override
    public List<Violation> getAllViolations() {
        return violationRepository.findAll();
    }

    @Override
    public List<Violation> getViolationsByPaid(boolean paid) {
        return violationRepository.findViolationByPaid(paid);
    }

    @Override
    public void payViolationById(UUID id) {
        Violation violation = getViolationById(id);
        violation.setPaid(true);
        violationRepository.save(violation);
    }

    @Override
    public ViolationsSummary getViolationsSummary() {
        Map<Boolean, Summary> summaries = violationRepository.findSummary();
        Summary paid = summaries.getOrDefault(true, zeroSummary);
        Summary unpaid = summaries.getOrDefault(false, zeroSummary);
        return new ViolationsSummary(paid, unpaid);
    }
}
