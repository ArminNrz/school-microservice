package com.school.academic.service.higlevel.student.handler;

import com.school.academic.domain.Student;
import com.school.academic.domain.Unit;
import com.school.academic.dto.unit.student.UnitStudentDTO;
import com.school.academic.dto.unit.student.UnitStudentRegistrationDTO;
import com.school.academic.service.entity.StudentService;
import com.school.academic.service.entity.UnitService;
import com.school.academic.service.entity.UnitStudentService;
import com.school.academic.service.jms.producer.StudentNotificationProducer;
import com.school.academic.service.thirdparty.FinanceClientService;
import com.school.clients.finance.dto.StudentFinanceRegisterResponse;
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
public class StudentRegisterUnitHandler {

    private final UnitStudentService unitStudentService;
    private final UnitService unitService;
    private final StudentService studentService;
    private final FinanceClientService financeClientService;
    private final StudentNotificationProducer notificationProducer;

    public UnitStudentDTO register(UnitStudentRegistrationDTO registrationDTO) {
        log.debug("Request to register Unit: {}, for Student: {}", registrationDTO.getUnitId(), registrationDTO.getStudentId());


        Unit unit = unitService.findById(registrationDTO.getUnitId());
        if (unit == null) {
            throw Problem.valueOf(Status.BAD_REQUEST, "No such unit exist");
        }

        checkLessonNotIterated(registrationDTO.getStudentId(), registrationDTO, unit);

        BigDecimal unitPoint = unit.getPoint();
        BigDecimal studentPointSum = checkAndGetStudentUnitPointSum(registrationDTO.getStudentId(), unitPoint);

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

    public StudentFinanceRegisterResponse endRegisterAndGetFinanceCode(Long id) {
        log.debug("Request to end register for student: {}, get fincance", id);

        Student student = studentService.endRegistration(id);

        BigDecimal pointSum = unitStudentService.getStudentUnitPointSum(id);

        StudentFinanceRegisterResponse response = financeClientService.register(id, pointSum);
        notificationProducer.produceEndTermNotification(student, pointSum);

        return response;
    }
}
