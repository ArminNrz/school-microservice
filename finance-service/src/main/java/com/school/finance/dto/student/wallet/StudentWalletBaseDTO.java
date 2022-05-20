package com.school.finance.dto.student.wallet;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class StudentWalletBaseDTO {
    private String studentId ;
    private Boolean active ;
    private BigDecimal balance ;
}
