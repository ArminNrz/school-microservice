package com.school.finance.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class StudentFinanceDTO {
    private String id ;
    private Long StudentId ;
    private BigDecimal cost ;
    private Boolean isPaid ;
    private LocalDateTime dateTime ;
    private LocalDateTime updateTime ;

}
