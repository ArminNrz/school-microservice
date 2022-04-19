package com.school.finance.service.entity;

import com.school.finance.domain.StudentFinance;
import com.school.finance.domain.StudentPayment;
import com.school.finance.repository.StudentPaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentPaymentService {

    private final StudentPaymentRepository repository;

    public StudentPayment addPayment(StudentFinance newFinance, StudentFinance oldFinance, BigDecimal amount) {
        log.debug("addPayment(), newFinance: {}, oldFinance: {}", newFinance, oldFinance);

        StudentPayment entity = new StudentPayment();

        entity.setInitialCost(oldFinance.getCost());
        entity.setAmount(amount);
        entity.setNewCost(newFinance.getCost());

        repository.insert(entity);
        return entity;
    }
 }
