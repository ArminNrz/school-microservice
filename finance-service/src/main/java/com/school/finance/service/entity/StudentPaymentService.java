package com.school.finance.service.entity;

import com.school.finance.domain.StudentFinance;
import com.school.finance.domain.StudentPayment;
import com.school.finance.dto.StudentPaymentDTO;
import com.school.finance.mapper.StudentPaymentMapper;
import com.school.finance.repository.StudentPaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentPaymentService {

    private final StudentPaymentRepository repository;
    private final StudentPaymentMapper mapper ;

    public void addPayment(StudentFinance newFinance, StudentFinance oldFinance, BigDecimal amount) {
        log.debug("addPayment(), newFinance: {}, oldFinance: {}", newFinance, oldFinance);

        StudentPayment entity = new StudentPayment();

        entity.setInitialCost(oldFinance.getCost());
        entity.setStudentFinanceId(oldFinance.getId());
        entity.setAmount(amount);
        entity.setNewCost(newFinance.getCost());

        repository.insert(entity);
    }

    public List<StudentPaymentDTO> getPaymentsByInvoiceCode(String invoiceCode) {
        log.debug("Request to get All Payments with InvoiceCode : {}" , invoiceCode);
        List<StudentPayment> paymentList = repository.getAllByStudentFinanceId(invoiceCode) ;
        if(paymentList.isEmpty()) {
            log.debug("No payments with the invoiceCode : {}", invoiceCode);
            throw Problem.valueOf(Status.NOT_FOUND , "No payment found for this student");
        }
        return paymentList.stream().map(mapper::toDTO).collect(Collectors.toList()) ;
    }
 }
