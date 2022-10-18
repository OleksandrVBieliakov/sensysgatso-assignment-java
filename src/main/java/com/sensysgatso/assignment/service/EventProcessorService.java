package com.sensysgatso.assignment.service;

import com.sensysgatso.assignment.model.Event;

public interface EventProcessorService {
    void processEvent(Event event);

    void processEvents();
}
