package com.school.finance.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document("student_payment")
@Data
@RequiredArgsConstructor
public class StudentPayment {
    @Id
    private String id ;

    private String studentFinanceId ;
    private BigDecimal initialCost ;
    private BigDecimal amount ;
    private BigDecimal newCost ;

    @CreationTimestamp
    private LocalDateTime createDateTime;


}
