package com.school.finance.dto.student.payment;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class StudentPaymentDTO {
    private String id;
    private BigDecimal amount;
    private LocalDateTime created;
}
