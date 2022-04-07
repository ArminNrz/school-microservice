package com.school.finance.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document("student_finance")
@Data
@RequiredArgsConstructor
public class StudentFinance implements Cloneable {

    @Id
    private String id;

    @Indexed(unique = true)
    private Long studentId;

    private BigDecimal pointSum;

    private BigDecimal cost;

    private Boolean isPaid;

    @CreatedDate
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;

    @Override
    public StudentFinance clone() {
        try {
            return (StudentFinance) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
