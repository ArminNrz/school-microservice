package com.school.academic.service.higlevel.report;


import com.school.academic.domain.Student;
import com.school.academic.dto.report.ReportPaymentDTO;
import com.school.academic.service.entity.StudentService;
import com.school.academic.service.thirdparty.FinanceClientService;
import com.school.clients.finance.dto.StudentPaymentsReportResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentReportHandler {
    private final FinanceClientService financeClientService ;
    private final StudentService studentService ;

    public ReportPaymentDTO getPaymentsReportByNationalCode(Long nationalCode) {
        log.debug("Request to get All payments with nationalCode : {}" , nationalCode);
        Student student = studentService.getByNationalCode(nationalCode) ;
        List<StudentPaymentsReportResponse> paymentList = new ArrayList<>() ;
        ReportPaymentDTO reportPaymentDTO = new ReportPaymentDTO() ;
        reportPaymentDTO.setFirstname(student.getFirstName());
        reportPaymentDTO.setLastname(student.getLastName());
        if(financeClientService.getPaymentsReport(nationalCode) == null) {
            throw Problem.valueOf(Status.NOT_FOUND , "No payments found for this student ") ;}
        else {
        reportPaymentDTO.setNationalCode(student.getNationalCode());
        reportPaymentDTO.setInvoiceCode(paymentList.get(0).getInvoiceCode()); }
        return reportPaymentDTO ;
    }
}
