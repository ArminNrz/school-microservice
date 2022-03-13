package com.school.academic.service.entity;

import com.school.academic.domain.Unit;
import com.school.academic.dto.unit.teacher.UnitTeacherDTO;
import com.school.academic.dto.unit.teacher.UnitTeacherRegistrationDTO;
import com.school.academic.mapper.UnitMapper;
import com.school.academic.repository.UnitRepository;
import com.school.academic.repository.data.LessonUnitPointSum;
import com.school.academic.repository.data.StudentUnitPointSum;
import com.school.academic.repository.data.TeacherUnitPointSum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UnitService {

    private final UnitRepository repository;
    private final UnitMapper mapper;

    /////////////////
    // CRUD
    ///////////////

    public Unit save(UnitTeacherRegistrationDTO registrationDTO) {
        Unit unit = mapper.toEntity(registrationDTO);
        return repository.save(unit);
    }

    public Unit findById(Long id) {
        Optional<Unit> unitOptional = repository.findById(id);
        if (unitOptional.isEmpty()) {
            log.warn("No such unit exist with id: {}", id);
            return null;
        }

        return unitOptional.get();
    }

    public List<Unit> findAllByIds(List<Long> ids) {
        return repository.findAllByIdIsIn(ids);
    }

    //////////////////
    // OTHER
    /////////////////

    public BigDecimal getTeacherPointSum(Long teacherId) {
        BigDecimal pointSum = BigDecimal.ZERO;
        TeacherUnitPointSum result = repository.countTeacherUnitPoint(teacherId);
        if (result != null && result.getSum() != null) {
            pointSum = result.getSum();
        }
        log.debug("Teacher: {} point sum, is: {}", teacherId, pointSum);
        return pointSum;
    }

    public BigDecimal getStudentPointSum(Long studentId) {
        BigDecimal pointSum = BigDecimal.ZERO ;
        StudentUnitPointSum result = repository.countStudentUnitPoint(studentId) ;
        if(result !=null && result.getSum() != null) {
            pointSum = result.getSum() ;
        }
         return pointSum ;
    }

    public List<Long> getUnitLessons(List<Long> ids) {
        log.debug("Request to get unit lessons");
        List<Unit> units = this.findAllByIds(ids);

        List<Long> lessonsId = units.stream()
                .filter(Unit::getIsActive)
                .map(Unit::getLessonId)
                .collect(Collectors.toList());
        log.debug("units: {}, lessonIds: {}", ids, lessonsId);
        return lessonsId;
    }

    public Unit findByTeacherAndLesson(Long teacherId, Long lessonId) {

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
    public UnitTeacherDTO register(Long teacherId, UnitTeacherRegistrationDTO registrationDTO) {

        BigDecimal pointSum = checkAndGetTeacherPointSum(teacherId, registrationDTO);

        checkLessonIsNotIterated(teacherId, registrationDTO);

        registrationDTO.setTeacherId(teacherId);
        Unit unit = this.save(registrationDTO);
        UnitTeacherDTO returnValue = mapper.toDTO(unit);
        returnValue.setTeacherPointSum(pointSum);

        return returnValue;
    }

    public BigDecimal countUnitsByLessonId (Long lessonId) {

        BigDecimal lessonPoints = BigDecimal.ZERO ;

        LessonUnitPointSum result = repository.countLessonUnitPoint(lessonId) ;

        if (result != null && result.getSum() != null) {
            lessonPoints = result.getSum() ;
        }
        log.debug("All points of this lesson is : {}", lessonPoints);
        return lessonPoints ;
    }

    public List<Unit> getUnitsByLessonId(Long lessonId) {

        log.debug("Enter to get Units by LessonId , {}" , lessonId);
        List<Unit> units = repository.getUnitsByLessonId(lessonId) ;
        return units ;
    }

    //////////////////
    // COMMON
    /////////////////

    private BigDecimal checkAndGetTeacherPointSum(Long teacherId, UnitTeacherRegistrationDTO registrationDTO) {

        BigDecimal pointSum = this.getTeacherPointSum(teacherId);
        pointSum = pointSum.add(registrationDTO.getPoint());

        if (pointSum.compareTo(BigDecimal.valueOf(15L)) > 0) {
            throw Problem.valueOf(Status.BAD_REQUEST, "Teacher get more unit than max size");
        }
        return pointSum;
    }

    private void checkLessonIsNotIterated(Long teacherId, UnitTeacherRegistrationDTO registrationDTO) {
        Unit foundUnit = this.findByTeacherAndLesson(teacherId, registrationDTO.getLessonId());
        if (foundUnit != null) {
            if (foundUnit.getIsActive()) {
                log.error("Teacher: {}, has same Lesson: {}, unit, and it is active", teacherId, registrationDTO.getLessonId());
                throw Problem.valueOf(Status.BAD_REQUEST, "This Teacher has same unit with this Lesson");
            }
        }
    }



}
