package com.school.finance.service.entity;

import com.school.finance.domain.WalletLog;
import com.school.finance.repository.WalletLogRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class WalletLogService {
    private final WalletLogRepository repository ;

    public WalletLog logTransaction (String walletId , BigDecimal amount) {
        log.debug("Request to save a transaction for wallet : {}" , walletId);
        WalletLog walletLog = new WalletLog();
        walletLog.setWalletId(walletId);
        walletLog.setAmount(amount);
        walletLog.setVerified(true);
        log.debug("log saved successfully ..");
        return repository.save(walletLog) ;

    }
}
