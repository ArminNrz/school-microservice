package com.school.finance.domain;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@RequiredArgsConstructor
@Document("student_wallet")
public class StudentWallet {

    @Id
    private String id ;

    @Indexed(unique = true)
    private Long studentId ;

    private BigDecimal balance ;
    private Boolean active ;

    @DBRef
    private List<WalletLog> walletLogs;

    @CreatedDate
    private LocalDateTime created ;

    @LastModifiedDate
    private LocalDateTime updated ;

}
