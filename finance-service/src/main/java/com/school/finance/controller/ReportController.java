package com.school.finance.controller;


import com.school.clients.finance.dto.StudentPaymentsReportResponse;
import com.school.finance.dto.StudentPaymentDTO;
import com.school.finance.mapper.StudentPaymentMapper;
import com.school.finance.service.highLevel.ReportServiceHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/finance/report")
public class ReportController {
    private final ReportServiceHandler reportService ;
    private final StudentPaymentMapper mapper ;
    @GetMapping("/{studentId}/payment-report")
    public ResponseEntity<List<StudentPaymentsReportResponse>> getPaymentsReport(@PathVariable("studentId") Long studentId) {
        log.info("Rest request to get All payments with studentId : {}" , studentId);
        List<StudentPaymentDTO> paymentsReport = reportService.getPaymentsReport(studentId) ;
        return ResponseEntity.ok(mapper.toReportResponseList(paymentsReport)) ;
    }
}
