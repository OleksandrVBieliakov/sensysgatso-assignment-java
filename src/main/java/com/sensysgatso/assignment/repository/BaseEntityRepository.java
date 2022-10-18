package com.sensysgatso.assignment.repository;

import com.sensysgatso.assignment.model.BaseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BaseEntityRepository<T extends BaseEntity> {
    T save(T entity);

    Optional<T> findById(UUID id);

    List<T> findAll();
}
