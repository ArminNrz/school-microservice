package com.school.clients.finance.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StudentFinanceRegisterRequest {
    private Long studentId;
    private BigDecimal pointSum;
}
