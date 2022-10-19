package com.sensysgatso.assignment.repository.inmemory;

import com.sensysgatso.assignment.model.Event;
import com.sensysgatso.assignment.repository.EventRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
@Profile("inmemory")
public class EventInMemoryRepository extends BaseInMemoryRepository<Event> implements EventRepository {
    @Override
    public List<Event> findEventsByProcessed(boolean processed) {
        return findAll().stream().filter(event -> event.getProcessed() == processed).collect(toList());
    }
}
