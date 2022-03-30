package com.school.academic.service.higlevel.teacher;

import com.school.academic.domain.Teacher;
import com.school.academic.dto.teacher.TeacherDetailsDTO;
import com.school.academic.dto.unit.teacher.UnitTeacherDetailsDTO;
import com.school.academic.mapper.TeacherMapper;
import com.school.academic.service.entity.TeacherService;
import com.school.academic.service.entity.UnitService;
import com.school.academic.service.entity.UnitTeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class TeacherUnitDetailsHandler {
    private final TeacherService service;
    private final TeacherMapper teacherMapper;
    private final UnitService unitService;
    private final UnitTeacherService unitTeacherService;

    public TeacherDetailsDTO getDetailsByNationalCode(Long nationalCode) {
        log.debug("Request to get teacher details with nationalCode: {}", nationalCode);
        TeacherDetailsDTO returnValue;
        Teacher foundTeacher = service.getByNationalCode(nationalCode);
        returnValue = teacherMapper.toDetailsDTO(foundTeacher);
        BigDecimal activeUnitPointSum = unitService.getTeacherPointSum(foundTeacher.getId());
        returnValue.setActiveUnitPointSum(activeUnitPointSum);
        List<UnitTeacherDetailsDTO> details = unitTeacherService.getTeacherUnitDetails(foundTeacher.getId());
        returnValue.setUnitTeacherDetailsDTOList(details);
        return returnValue;
    }
}
