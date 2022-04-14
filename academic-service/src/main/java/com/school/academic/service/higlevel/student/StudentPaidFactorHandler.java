package com.school.academic.service.higlevel.student;

import com.school.academic.dto.student.StudentFactorDTO;
import com.school.academic.service.thirdparty.FinanceClientService;
import com.school.clients.finance.dto.StudentFinanceRegisterResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class StudentPaidFactorHandler {

    private final FinanceClientService  financeClientService;
    public List<StudentFactorDTO> getFinalStudents () {
        log.debug("Request to get Final students ...");
        List<StudentFactorDTO> studentFactorDTOS = new ArrayList<>() ;
        List<StudentFinanceRegisterResponse> finalFactors = financeClientService.getPaidFactor() ;

        for(StudentFinanceRegisterResponse factors : finalFactors) {
            for (StudentFactorDTO studentFactorDTO : studentFactorDTOS){
                studentFactorDTO.setInvoiceCode(factors.getInvoiceCode());
            }

        }
        return null ;
    }
}
