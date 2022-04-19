package com.school.academic.service.higlevel.student.handler;


import com.school.academic.domain.Student;
import com.school.academic.dto.student.StudentFactorDTO;
import com.school.academic.dto.unit.student.UnitStudentDetail;
import com.school.academic.mapper.StudentMapper;
import com.school.academic.service.entity.StudentService;
import com.school.academic.service.entity.UnitStudentService;
import com.school.academic.service.thirdparty.FinanceClientService;
import com.school.clients.finance.dto.StudentFactorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.math.BigDecimal;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class StudentFactorHandler {

    private final StudentService studentService ;
    private final FinanceClientService financeClientService ;
    private final UnitStudentService unitStudentService;
    private final StudentMapper mapper ;

    public StudentFactorDTO getFactorByNationalCode (Long nationalCode) {
        log.debug("Request to get factor By nationalCode : {}" ,nationalCode);
        Student student = studentService.getByNationalCode(nationalCode);
        if (student == null) {
            throw Problem.valueOf(Status.NOT_FOUND, "No such student exist");
        }
        StudentFactorDTO dto = mapper.toFactorDTO(student) ;
        BigDecimal pointSum = unitStudentService.getStudentUnitPointSum(student.getId()) ;
        dto.setPointSum(pointSum);
        List<UnitStudentDetail> unitList = unitStudentService.getStudentUnitDetails(student.getId()) ;
        StudentFactorResponse factorResponse = financeClientService.getFactor(student.getId()) ;
        dto.setUnitList(unitList);
        dto.setInvoiceCode(factorResponse.getInvoiceCode());
        dto.setCost(factorResponse.getCost());
        return dto ;
    }
}
