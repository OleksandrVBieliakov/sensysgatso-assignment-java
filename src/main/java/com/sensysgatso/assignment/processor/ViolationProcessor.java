package com.sensysgatso.assignment.processor;

import com.sensysgatso.assignment.model.Event;
import com.sensysgatso.assignment.model.Violation;
import com.sensysgatso.assignment.repository.ViolationRepository;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class ViolationProcessor implements EventProcessor {

    private final ViolationRepository violationRepository;

    private final BigDecimal fee;

    public ViolationProcessor(ViolationRepository violationRepository, BigDecimal fee) {
        this.violationRepository = violationRepository;
        this.fee = fee;
    }

    @Override
    public void processEvent(Event event) {
        Violation violation = violationRepository.save(new Violation(null, event.getId(), fee, false));
        log.info("New {} for {}", violation, event);
    }
}
