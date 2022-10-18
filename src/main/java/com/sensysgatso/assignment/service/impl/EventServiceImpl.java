package com.sensysgatso.assignment.service.impl;

import com.sensysgatso.assignment.model.Event;
import com.sensysgatso.assignment.repository.EventRepository;
import com.sensysgatso.assignment.service.EventProcessorService;
import com.sensysgatso.assignment.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    private final EventProcessorService processorService;

    public EventServiceImpl(EventRepository eventRepository, EventProcessorService processorService) {
        this.eventRepository = eventRepository;
        this.processorService = processorService;
    }

    @Override
    public void saveEvent(Event event) {
        log.debug("Saving {}", event);
        eventRepository.save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public void processEvents() {
        processorService.processEvents();
    }
}
