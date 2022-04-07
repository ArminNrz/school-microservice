package com.school.finance.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class StudentFinanceDTO {
    private String id;
    private Long StudentId;
    private BigDecimal cost;
    private Boolean isPaid;
    private LocalDateTime created;
    private LocalDateTime updated;
}
