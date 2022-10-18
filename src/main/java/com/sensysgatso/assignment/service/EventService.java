package com.sensysgatso.assignment.service;

import com.sensysgatso.assignment.model.Event;

import java.util.List;

public interface EventService {
    void saveEvent(Event event);

    List<Event> getAllEvents();

    void processEvents();
}
