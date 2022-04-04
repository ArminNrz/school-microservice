package com.school.finance.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class StudentPaymentDTO {
    private String id ;
    private String studentFinanceId ;
    private BigDecimal initialCost ;
    private BigDecimal amount ;
    private BigDecimal newCost ;
    private LocalDateTime createDateTime ;

}
