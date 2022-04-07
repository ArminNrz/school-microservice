package com.school.clients.finance.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StudentFactorResponse {
    private BigDecimal pointSum ;
    private String invoiceCode  ;
    private BigDecimal cost ;
}
