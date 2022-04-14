package com.school.academic.service.thirdparty;

import com.school.academic.mapper.StudentMapper;
import com.school.clients.finance.StudentFinanceClient;
import com.school.clients.finance.dto.StudentFactorResponse;
import com.school.clients.finance.dto.StudentFinanceRegisterRequest;
import com.school.clients.finance.dto.StudentFinanceRegisterResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FinanceClientService {

    private final StudentFinanceClient studentFinanceClient;
    private final StudentMapper studentMapper;

    public StudentFinanceRegisterResponse register(Long studentId, BigDecimal pointSum) {
        log.debug("Try to send request to Finance-service, to register Student finance: {}", studentId);
        StudentFinanceRegisterRequest request = studentMapper.toStudentFinanceRequest(studentId, pointSum);
        StudentFinanceRegisterResponse response = studentFinanceClient.register(request);
        log.debug("Send request to Finance-service and response: {}", response);
        return response;
    }
    public StudentFactorResponse getFactor(Long studentId) {
        log.debug("try to send request to Finance-service, to get StudentFactor : {}", studentId);
        StudentFactorResponse response = studentFinanceClient.getFactor(studentId) ;
        log.debug("Send request to Finance-service and response: {}", response);
        return response ;
    }
    public List<StudentFinanceRegisterResponse> getPaidFactor() {
        log.debug("try to get Paid factors from finance service ....");
        List<StudentFinanceRegisterResponse> paidFactors = studentFinanceClient.getPaidFactor() ;
        return  paidFactors ;
    }
}
