package com.school.academic.service.thirdparty;

import com.school.clients.finance.StudentFinanceClient;
import com.school.clients.finance.dto.StudentFinanceRegisterRequest;
import com.school.clients.finance.dto.StudentFinanceRegisterResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class FinanceClientService {

    private final StudentFinanceClient studentFinanceClient;

    public StudentFinanceRegisterResponse register(Long studentId, BigDecimal pointSum) {
        log.debug("Try to send request to Finance-service, to register Student finance: {}", studentId);
        StudentFinanceRegisterRequest request = new StudentFinanceRegisterRequest();
        request.setStudentId(studentId);
        request.setPointSum(pointSum);
        StudentFinanceRegisterResponse response = studentFinanceClient.register(request);
        log.debug("Send request to Finance-service and response: {}", response);
        return response;
    }
}
