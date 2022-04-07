package com.school.finance.dto.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class StudentFinancePaymentDTO {

    @JsonIgnore
    private Long studentId;

    @NotNull
    private BigDecimal amount;
}
