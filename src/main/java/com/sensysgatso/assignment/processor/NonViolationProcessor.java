package com.sensysgatso.assignment.processor;

import com.sensysgatso.assignment.model.Event;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NonViolationProcessor implements EventProcessor {
    @Override
    public void processEvent(Event event) {
        log.info("No violation for {}", event);
    }
}
