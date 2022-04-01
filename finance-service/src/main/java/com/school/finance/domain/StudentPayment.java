package com.school.finance.domain;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document("student_payment")
@Data
@RequiredArgsConstructor
public class StudentPayment {

    @Id
    private String id ;
    private Long studentId ;
    private BigDecimal amount ;

}
