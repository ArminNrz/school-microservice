package com.school.finance.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document("student_finance")
@Data
@RequiredArgsConstructor
public class StudentFinance {

    @Id
    private String id;

    @Indexed(unique = true)
    private Long studentId;

    private BigDecimal pointSum;

    private BigDecimal cost;

    private Boolean isPaid;
}
