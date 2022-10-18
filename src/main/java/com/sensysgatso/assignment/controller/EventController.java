package com.sensysgatso.assignment.controller;

import com.sensysgatso.assignment.dto.Events;
import com.sensysgatso.assignment.model.Event;
import com.sensysgatso.assignment.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void saveEvent(@RequestBody Event event) {
        eventService.saveEvent(event);
    }

    @GetMapping()
    Events getAllEvents() {
        return new Events(eventService.getAllEvents());
    }

    @PostMapping("/process")
    void processEvents() {
        eventService.processEvents();
    }
}
