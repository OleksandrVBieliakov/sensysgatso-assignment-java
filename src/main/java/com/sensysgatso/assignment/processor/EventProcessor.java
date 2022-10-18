package com.sensysgatso.assignment.processor;

import com.sensysgatso.assignment.model.Event;

public interface EventProcessor {
    void processEvent(Event event);
}
