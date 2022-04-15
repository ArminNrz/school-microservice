package com.school.finance.service.highLevel;

import com.school.finance.domain.StudentFinance;
import com.school.finance.dto.StudentPaymentDTO;
import com.school.finance.service.entity.StudentFinanceService;
import com.school.finance.service.entity.StudentPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReportServiceHandler {

    private final StudentFinanceService financeService ;
    private final StudentPaymentService paymentService ;

    public List<StudentPaymentDTO> getPaymentsReport(Long studentId) {
        log.debug("Request to get payments by StudentId : {}",studentId);
        Optional<StudentFinance> studentFinance = financeService.getByStudentId(studentId) ;
        if(studentFinance.isEmpty())
            throw Problem.valueOf(Status.NOT_FOUND,"No factor found for this student");
        return paymentService.getPaymentsByInvoiceCode(studentFinance.get().getId());
    }


}
