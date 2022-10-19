package com.sensysgatso.assignment.repository;

import com.sensysgatso.assignment.dto.Summary;
import com.sensysgatso.assignment.model.Violation;

import java.util.List;

public interface ViolationRepository extends BaseEntityRepository<Violation> {

    List<Violation> findViolationByPaid(boolean paid);

    Summary findSummary(boolean paid);
}
