package com.school.finance.domain;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Document("wallet_log")
public class WalletLog {

    private String id ;
    private String WalletId ;
    private BigDecimal amount ;
    private Boolean verified ;
    @CreatedDate
    private LocalDateTime created ;




}
