package com.school.clients.finance.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ChargeWalletResponse {
    private String walletId ;
    private Long studentId ;
    private String transactionId ;
    private BigDecimal currentBalance ;
}
