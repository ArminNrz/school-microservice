package com.school.academic.service.highlevel;

import com.school.academic.domain.Unit;
import com.school.academic.dto.student.StudentDTO;
import com.school.academic.dto.student.StudentDetailsDTO;
import com.school.academic.dto.unit.student.UnitStudentDetailsDTO;
import com.school.academic.mapper.UnitMapper;
import com.school.academic.service.entity.StudentService;
import com.school.academic.service.entity.UnitService;
import com.school.academic.service.entity.UnitStudentService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Data
public class StudentGetDetails {
    private final StudentService Studentservice;
    private final UnitStudentService unitStudentService;
    private final UnitService unitService;
    private final UnitMapper mapper;


    public StudentDetailsDTO getStudentDetails(Long nationalCode) {


        log.debug("Request to get Student Detail by NationalCode : {}", nationalCode);
        StudentDTO studentDto = Studentservice.findByNationalCode(nationalCode);

        if (studentDto == null) {
            throw Problem.valueOf(Status.BAD_REQUEST, "No Student found ... ! ");
        }

        StudentDetailsDTO dto = new StudentDetailsDTO();
        dto.setFirstName(studentDto.getFirstName());
        dto.setLastName(studentDto.getLastName());
        dto.setNationalCode(studentDto.getNationalCode());
        dto.setTotalPoint(unitStudentService.getStudentUnitPointSum(studentDto.getId()));
        List<Long> unitIds = unitStudentService.getStudentUnits(studentDto.getId());
        List<Unit> units = unitService.findAllByIds(unitIds);

        List<UnitStudentDetailsDTO> studentUnits = units.stream()
                .filter(Unit::getIsActive)
                .map(mapper::toUnitDetailsDTO).collect(Collectors.toList());
        dto.setStudentUnits(studentUnits);

        return dto;


    }
}
