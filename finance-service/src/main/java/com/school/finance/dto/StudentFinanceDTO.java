package com.school.finance.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class StudentFinanceDTO {
    private String id;
    private Long StudentId;
    private List<StudentPaymentDTO> paymentDetails;
    private BigDecimal cost;
    private Boolean isPaid;
    private LocalDateTime created;
    private LocalDateTime updated;
}
