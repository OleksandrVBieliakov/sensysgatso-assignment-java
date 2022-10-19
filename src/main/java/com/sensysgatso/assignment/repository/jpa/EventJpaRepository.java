package com.sensysgatso.assignment.repository.jpa;

import com.sensysgatso.assignment.model.Event;
import com.sensysgatso.assignment.repository.EventRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EventJpaRepository extends JpaRepository<Event, UUID>, EventRepository {
    @Override
    List<Event> findEventsByProcessed(boolean processed);
}
