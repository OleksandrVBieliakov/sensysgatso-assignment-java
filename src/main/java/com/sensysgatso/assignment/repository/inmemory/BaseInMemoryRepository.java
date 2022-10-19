package com.sensysgatso.assignment.repository.inmemory;

import com.sensysgatso.assignment.model.BaseEntity;
import com.sensysgatso.assignment.repository.BaseEntityRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

abstract public class BaseInMemoryRepository<T extends BaseEntity> implements BaseEntityRepository<T> {
    private final Map<UUID, T> entities = new ConcurrentHashMap<>();

    @Override
    public T save(T entity) {
        if (entity.getId() == null)
            entity.setId(UUID.randomUUID());
        entities.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<T> findById(UUID id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(entities.values());
    }
}
