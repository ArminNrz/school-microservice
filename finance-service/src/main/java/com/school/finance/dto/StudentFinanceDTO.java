package com.school.finance.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class StudentFinanceDTO {
    private String id ;
    private Long StudentId ;
    private BigDecimal cost ;
    private Boolean isPaid ;
}
