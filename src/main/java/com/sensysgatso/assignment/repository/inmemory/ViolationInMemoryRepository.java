package com.sensysgatso.assignment.repository.inmemory;

import com.sensysgatso.assignment.dto.Summary;
import com.sensysgatso.assignment.model.Violation;
import com.sensysgatso.assignment.repository.ViolationRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
@Profile("inmemory")
public class ViolationInMemoryRepository extends BaseInMemoryRepository<Violation> implements ViolationRepository {
    @Override
    public List<Violation> findViolationByPaid(boolean paid) {
        return findAll().stream().filter(violation -> violation.getPaid() == paid).collect(toList());
    }

    @Override
    public Summary findSummary(boolean paid) {
        List<BigDecimal> fines = findAll().stream().filter(violation -> violation.getPaid() == paid).map(Violation::getFine).collect(toList());
        int count = fines.size();
        BigDecimal fine = fines.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        return new Summary(count, fine);
    }
}
