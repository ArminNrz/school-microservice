package com.school.academic.service.higlevel.student.handler;

import com.school.academic.domain.Student;
import com.school.academic.dto.student.StudentDetailsDTO;
import com.school.academic.dto.unit.student.UnitStudentDetail;
import com.school.academic.mapper.StudentMapper;
import com.school.academic.service.entity.StudentService;
import com.school.academic.service.entity.UnitStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class StudentUnitDetailsHandler {

    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final UnitStudentService unitStudentService;

    public StudentDetailsDTO getDetailsByNationalCode(Long nationalCode) {
        log.debug("Request to get student details with nationalCode: {}", nationalCode);

        StudentDetailsDTO returnValue;

        Student foundStudent = studentService.getByNationalCode(nationalCode);
        returnValue = studentMapper.toDetailsDTO(foundStudent);

        BigDecimal pointSum = unitStudentService.getStudentUnitPointSum(foundStudent.getId());
        returnValue.setUnitPointSum(pointSum);

        List<UnitStudentDetail> details = unitStudentService.getStudentUnitDetails(foundStudent.getId());
        log.debug("details: {}", details);

        returnValue.setUnitDetails(details);
        return returnValue;
    }
}
