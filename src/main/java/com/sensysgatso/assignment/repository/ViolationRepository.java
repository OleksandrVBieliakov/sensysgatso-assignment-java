package com.sensysgatso.assignment.repository;

import com.sensysgatso.assignment.dto.Summary;
import com.sensysgatso.assignment.model.Violation;

import java.util.List;
import java.util.Map;

public interface ViolationRepository extends BaseEntityRepository<Violation> {

    List<Violation> findViolationByPaid(boolean paid);

    Map<Boolean, Summary> findSummary();
}
