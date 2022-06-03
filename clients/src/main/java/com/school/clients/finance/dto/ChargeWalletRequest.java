package com.school.clients.finance.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ChargeWalletRequest {
    private long studentId ;
    private BigDecimal amount ;

}
