package com.school.academic.service.higlevel;

import com.school.academic.domain.Student;
import com.school.academic.domain.Unit;
import com.school.academic.domain.UnitStudent;
import com.school.academic.dto.unit.student.UnitStudentDTO;
import com.school.academic.dto.unit.student.UnitStudentRegistrationDTO;
import com.school.academic.service.entity.StudentService;
import com.school.academic.service.entity.UnitService;
import com.school.academic.service.entity.UnitStudentService;
import com.school.academic.service.thirdparty.FinanceClientService;
import com.school.clients.finance.dto.StudentFinanceRegisterResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentRegisterUnitManager {

    private final UnitStudentService unitStudentService;
    private final UnitService unitService;
    private final StudentService studentService;
    private final FinanceClientService financeClientService;

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


    public StudentFinanceRegisterResponse showStudentFinancialInvoice(Long studentId) {
        BigDecimal pointSum = unitStudentService.getStudentUnitPointSum(studentId);
        Unit unit = unitService.getByPointGreaterThanEqual(BigDecimal.valueOf(10));
        Student student = studentService.getOne(studentId);
        UnitStudent unitStudent = new UnitStudent();
        unitStudent.setUnit(unit);
        unitStudent.setStudent(student);
        unitStudentService.save(unitStudent);

        return financeClientService.register(studentId, pointSum);
    }
}
