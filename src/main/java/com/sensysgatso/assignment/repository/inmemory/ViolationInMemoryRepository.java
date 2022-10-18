package com.sensysgatso.assignment.repository.inmemory;

import com.sensysgatso.assignment.dto.Summary;
import com.sensysgatso.assignment.model.Violation;
import com.sensysgatso.assignment.repository.ViolationRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Repository
public class ViolationInMemoryRepository extends BaseInMemoryRepository<Violation> implements ViolationRepository {
    //SQL analog: select * from violation where paid=:paid
    @Override
    public List<Violation> findViolationByPaid(boolean paid) {
        return findAll().stream().filter(violation -> violation.getPaid() == paid).collect(toList());
    }

    //SQL analog: select paid, count(*), sum(fine) from violation group by paid
    @Override
    public Map<Boolean, Summary> findSummary() {
        return findAll().stream()
                .collect(groupingBy(Violation::getPaid, collectingAndThen(toList(), list -> {
                    long count = list.size();
                    BigDecimal fine = list.stream().map(Violation::getFine).reduce(BigDecimal.ZERO, BigDecimal::add);
                    return new Summary((int) count, fine);
                })));
    }
}
