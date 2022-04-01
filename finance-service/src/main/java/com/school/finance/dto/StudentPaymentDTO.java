package com.school.finance.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class StudentPaymentDTO {
    private String id ;
    private Long studentId ;
    private BigDecimal amount ;
}
