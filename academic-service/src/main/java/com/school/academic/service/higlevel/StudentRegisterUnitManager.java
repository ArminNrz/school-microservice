package com.school.academic.service.higlevel;

import com.school.academic.domain.Student;
import com.school.academic.domain.Unit;
import com.school.academic.dto.student.StudentDetailDTO;
import com.school.academic.dto.unit.student.UnitDetailsDTO;
import com.school.academic.dto.unit.student.UnitStudentDTO;
import com.school.academic.dto.unit.student.UnitStudentRegistrationDTO;
import com.school.academic.mapper.UnitMapper;
import com.school.academic.service.entity.StudentService;
import com.school.academic.service.entity.UnitService;
import com.school.academic.service.entity.UnitStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentRegisterUnitManager {

    private final UnitStudentService unitStudentService;
    private final UnitService unitService;
    private final StudentService studentService;
    private final UnitMapper unitMapper;

    public UnitStudentDTO register(Long studentId, UnitStudentRegistrationDTO registrationDTO) {
        log.debug("Request to register Unit: {}, for Student: {}", registrationDTO.getUnitId(), studentId);

        registrationDTO.setStudentId(studentId);

        Unit unit = unitService.findById(registrationDTO.getUnitId());
        if (unit == null) {
            throw Problem.valueOf(Status.BAD_REQUEST, "No such unit exist");
        }

        checkLessonNotIterated(studentId, registrationDTO, unit);

        BigDecimal unitPoint = unit.getPoint();
        BigDecimal studentPointSum = checkAndGetStudentUnitPointSum(studentId, unitPoint);

        UnitStudentDTO returnValue = unitStudentService.register(registrationDTO);
        returnValue.setPoint(unitPoint);
        returnValue.setStudentPointSum(studentPointSum);

        return returnValue;
    }

    private void checkLessonNotIterated(Long studentId, UnitStudentRegistrationDTO registrationDTO, Unit unit) {
        List<Long> studentUnits = unitStudentService.getStudentUnits(studentId);
        List<Long> studentLessonIds = unitService.getUnitLessons(studentUnits);
        boolean isLessonIterated = studentLessonIds.stream()
                .anyMatch(studentLessonId -> studentLessonId.equals(unit.getLessonId()));
        if (isLessonIterated) {
            log.error("Student: {}, Can not register unit: {}, Because lesson is iterated", studentId, registrationDTO.getUnitId());
            throw Problem.valueOf(Status.BAD_REQUEST, "Unit lesson is iterated for this Student");
        }
    }

    private BigDecimal checkAndGetStudentUnitPointSum(Long studentId, BigDecimal unitPoint) {

        BigDecimal studentUnitPointSum = unitStudentService.getStudentUnitPointSum(studentId);
        studentUnitPointSum = studentUnitPointSum.add(unitPoint);

        if (studentUnitPointSum.compareTo(BigDecimal.valueOf(10L)) > 0) {
            log.error("Student: {}, has more unit point than max", studentId);
            throw Problem.valueOf(Status.BAD_REQUEST, "Student has more unit point than max");
        }

        log.debug("Student: {}, pointSum: {}", studentId, studentUnitPointSum);
        return studentUnitPointSum;
    }

    public StudentDetailDTO getStudentDetails(Long nationalCode) {
        log.debug("Request was sent to getStudentDetails by nationalCode: {}", nationalCode);

        Student student = studentService.getByNationalCode(nationalCode);
        if (student == null) {
            log.error("No student exists by nationalCode: {}", nationalCode);
            throw Problem.valueOf(Status.BAD_REQUEST, "No student exists by nationalCode");
        }

        BigDecimal sumOfPoints = unitStudentService.getStudentUnitPointSum(student.getId());
        List<Unit> unitList = unitService.getAllUnitsByStudentId(student.getId());

        List<UnitDetailsDTO> unitDetailsDTOList = unitList.stream()
                .map(unitMapper::toUnitDetailsDTO).collect(Collectors.toList());

        return createStudentDetailDTO(student, sumOfPoints, unitDetailsDTOList);

    }

    private StudentDetailDTO createStudentDetailDTO(Student student, BigDecimal sumOfPoints, List<UnitDetailsDTO> unitDetailsDTOList) {
        StudentDetailDTO response = new StudentDetailDTO();
        response.setStudentUnits(unitDetailsDTOList);
        response.setPointSum(sumOfPoints);
        response.setFirstName(student.getFirstName());
        response.setLastName(student.getLastName());
        response.setNationalCode(student.getNationalCode());

        return response;
    }
}
