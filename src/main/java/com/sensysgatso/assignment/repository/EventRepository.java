package com.sensysgatso.assignment.repository;

import com.sensysgatso.assignment.model.Event;

import java.util.List;

public interface EventRepository extends BaseEntityRepository<Event> {
    List<Event> findEventsByProcessed(boolean processed);
}
