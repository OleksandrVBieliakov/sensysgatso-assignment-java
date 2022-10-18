package com.sensysgatso.assignment.service.impl;

import com.sensysgatso.assignment.config.ConfigProperties;
import com.sensysgatso.assignment.model.Event;
import com.sensysgatso.assignment.repository.EventRepository;
import com.sensysgatso.assignment.repository.ViolationRepository;
import com.sensysgatso.assignment.service.EventProcessorService;
import com.sensysgatso.assignment.processor.EventProcessor;
import com.sensysgatso.assignment.processor.NonViolationProcessor;
import com.sensysgatso.assignment.processor.ViolationProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EventProcessorServiceImpl implements EventProcessorService {

    public static final String SPEED = "SPEED";
    public static final String REDLIGHT = "REDLIGHT";
    private final ConfigProperties configProperties;
    private final EventRepository eventRepository;
    private final ViolationRepository violationRepository;
    private EventProcessor speedViolationProcessor;
    private EventProcessor redlightViolationProcessor;
    private final EventProcessor nonEventProcessor = new NonViolationProcessor();

    public EventProcessorServiceImpl(ConfigProperties configProperties,
                                     EventRepository eventRepository,
                                     ViolationRepository violationRepository) {
        this.configProperties = configProperties;
        this.eventRepository = eventRepository;
        this.violationRepository = violationRepository;
    }

    @PostConstruct
    void init() {
        Map<String, BigDecimal> fees = configProperties.getViolation().getFees();
        speedViolationProcessor = new ViolationProcessor(violationRepository, fees.get(SPEED));
        redlightViolationProcessor = new ViolationProcessor(violationRepository, fees.get(REDLIGHT));
    }

    private EventProcessor getProcessor(String eventType) {
        if (eventType.equalsIgnoreCase(SPEED))
            return speedViolationProcessor;
        else if (eventType.equalsIgnoreCase(REDLIGHT))
            return redlightViolationProcessor;
        else
            return nonEventProcessor;
    }

    @Override
    public void processEvent(Event event) {
        EventProcessor processor = getProcessor(event.getEventType());
        processor.processEvent(event);
        event.setProcessed(true);
        eventRepository.save(event);
    }

    @Override
    @Scheduled(fixedDelay = 10000L, initialDelay = 10000L)
    public void processEvents() {
        List<Event> unprocessedEvents = eventRepository.findEventsByProcessed(false);
        if (!unprocessedEvents.isEmpty()) {
            log.info("Processing {} events", unprocessedEvents.size());
            for (Event event : unprocessedEvents) {
                processEvent(event);
            }
        }
    }
}
