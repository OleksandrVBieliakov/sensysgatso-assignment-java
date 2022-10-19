package com.sensysgatso.assignment.repository.jpa;

import com.sensysgatso.assignment.dto.Summary;
import com.sensysgatso.assignment.model.Violation;
import com.sensysgatso.assignment.repository.ViolationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ViolationJpaRepository extends JpaRepository<Violation, UUID>, ViolationRepository {
    @Override
    List<Violation> findViolationByPaid(boolean paid);

    @Override
    @Query("select new com.sensysgatso.assignment.dto.Summary(count(id), sum(fine)) from Violation where paid = :paid")
    Summary findSummary(boolean paid);

}
