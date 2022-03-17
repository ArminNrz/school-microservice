package com.school.academic.service.higlevel;


import com.school.academic.service.entity.StudentService;
import com.school.academic.service.entity.UnitStudentService;
import com.school.academic.service.thirdparty.FinanceClientService;
import com.school.clients.finance.dto.StudentFinanceRegisterResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@Data
@RequiredArgsConstructor
public class EndRegisteringUnitHandler {


    private StudentService studentService;
    private UnitStudentService unitStudentService;
    private FinanceClientService financeClientService;

    public StudentFinanceRegisterResponse endRegister(Long studentId) {

        log.debug("Request to end registering of units for Student : {}", studentId);
        studentService.endRegister(studentId);
        BigDecimal pointSum = unitStudentService.getStudentUnitPointSum(studentId);
        log.debug("Request is sending to Finance client for Student : {} and pointSum : {}", studentId, pointSum);
        StudentFinanceRegisterResponse response = financeClientService.register(studentId, pointSum);

        return response;
    }
}
