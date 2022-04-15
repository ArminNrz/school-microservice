package com.school.academic.controller;


import com.school.academic.dto.report.ReportPaymentDTO;
import com.school.academic.service.higlevel.report.PaymentReportHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/academic/reports/")
public class PaymentReportController {

    private final PaymentReportHandler reportHandler ;
    @GetMapping("/{nationalCode}/payment-history")
    public ResponseEntity<ReportPaymentDTO> paymentHistory(@PathVariable("nationalCode") Long nationalCode) {
        log.info("Rest request to get Student payments with nationalCode : {}" , nationalCode);
        ReportPaymentDTO reportPaymentDTO = reportHandler.getPaymentsReportByNationalCode(nationalCode) ;
        return ResponseEntity.ok(reportPaymentDTO) ;
    }

}
