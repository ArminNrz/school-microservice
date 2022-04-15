package com.school.clients.finance.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class StudentPaymentsReportResponse {

    private String PaymentId ;
    private BigDecimal initialCost ;
    private String invoiceCode ;
    private BigDecimal amount ;
    private BigDecimal newCost ;
    private LocalDateTime createDateTime ;
}
