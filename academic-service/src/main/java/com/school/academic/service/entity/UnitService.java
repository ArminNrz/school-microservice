package com.school.academic.service.entity;

import com.school.academic.domain.Unit;
import com.school.academic.dto.unit.UnitDTO;
import com.school.academic.dto.unit.UnitRegistrationDTO;
import com.school.academic.mapper.UnitMapper;
import com.school.academic.repository.UnitRepository;
import com.school.academic.repository.data.TeacherUnitPointSum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UnitService {

    private final UnitRepository repository;
    private final UnitMapper mapper;

    public Unit save(UnitRegistrationDTO registrationDTO) {
        Unit unit = mapper.toEntity(registrationDTO);
        return repository.save(unit);
    }

    public BigDecimal getTeacherPointSum(Long teacherId) {
        TeacherUnitPointSum result = repository.countTeacherUnitPoint(teacherId);
        BigDecimal pointSum = result.getSum();
        log.debug("Teacher: {} point sum, is: {}", teacherId, pointSum);
        return pointSum;
    }

    private Unit findByTeacherAndLesson(Long teacherId, Long lessonId) {

        Optional<Unit> unitOptional = repository.findByTeacherIdAndLessonId(teacherId, lessonId);
        if (unitOptional.isEmpty()) {
            log.debug("Can not found any unit with Teacher: {}, Lesson: {}", teacherId, lessonId);
            return null;
        }

        Unit returnValue = unitOptional.get();
        log.debug("Found with Teacher: {}, Lesson: {}, Unit: {}", teacherId, lessonId, returnValue);
        return returnValue;
    }

    @Transactional
    public UnitDTO register(Long teacherId, UnitRegistrationDTO registrationDTO) {

        BigDecimal pointSum = this.getTeacherPointSum(teacherId);
        pointSum = pointSum.add(registrationDTO.getPoint());

        if (pointSum.compareTo(BigDecimal.valueOf(15L)) > 0) {
            throw Problem.valueOf(Status.BAD_REQUEST, "Teacher get more unit than max size");
        }

        checkLessonIsNotItterated(teacherId, registrationDTO);

        registrationDTO.setTeacherId(teacherId);
        Unit unit = this.save(registrationDTO);
        UnitDTO returnValue = mapper.toDTO(unit);
        returnValue.setTeacherPointSum(pointSum);

        return returnValue;
    }

    private void checkLessonIsNotItterated(Long teacherId, UnitRegistrationDTO registrationDTO) {
        Unit foundUnit = this.findByTeacherAndLesson(teacherId, registrationDTO.getLessonId());
        if (foundUnit != null) {

            if (foundUnit.getIsActive()) {
                log.error("Teacher: {}, has same Lesson: {}, unit, and it is active", teacherId, registrationDTO.getLessonId());
                throw Problem.valueOf(Status.BAD_REQUEST, "This Teacher has same unit with this Lesson");
            }

        }
    }
}
