package com.school.finance.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTime ;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updateTime ;
}
