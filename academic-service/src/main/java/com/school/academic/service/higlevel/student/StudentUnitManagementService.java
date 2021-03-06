package com.school.academic.service.higlevel.student;

import com.school.academic.dto.student.StudentDetailsDTO;
import com.school.academic.dto.student.StudentFactorDTO;
import com.school.academic.dto.unit.student.UnitStudentDTO;
import com.school.academic.dto.unit.student.UnitStudentRegistrationDTO;
import com.school.academic.service.higlevel.student.handler.StudentFactorHandler;
import com.school.academic.service.higlevel.student.handler.StudentRegisterUnitHandler;
import com.school.academic.service.higlevel.student.handler.StudentUnitDetailsHandler;
import com.school.clients.finance.dto.StudentFinanceRegisterResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentUnitManagementService {

    private final StudentRegisterUnitHandler registerUnitHandler;
    private final StudentUnitDetailsHandler detailsHandler;
    private final StudentFactorHandler factorHandler ;

    public UnitStudentDTO register(Long studentId, UnitStudentRegistrationDTO registrationDTO) {
        registrationDTO.setStudentId(studentId);
        return registerUnitHandler.register(registrationDTO);
    }

    public StudentDetailsDTO getDetailsByNationalCode(Long nationalCode) {
        return detailsHandler.getDetailsByNationalCode(nationalCode);
    }

    public StudentFinanceRegisterResponse endRegisterAndGetFinanceCode(Long id) {
        return registerUnitHandler.endRegisterAndGetFinanceCode(id);
    }

    public StudentFactorDTO getFactor(Long studentId){
        return factorHandler.getFactorByNationalCode(studentId) ;
    }
}
