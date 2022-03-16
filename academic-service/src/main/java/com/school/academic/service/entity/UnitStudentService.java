package com.school.academic.service.entity;

import com.school.academic.domain.UnitStudent;
import com.school.academic.dto.unit.student.UnitStudentDTO;
import com.school.academic.dto.unit.student.UnitStudentDetail;
import com.school.academic.dto.unit.student.UnitStudentRegistrationDTO;
import com.school.academic.mapper.UnitStudentMapper;
import com.school.academic.repository.UnitStudentRepository;
import com.school.academic.repository.data.StudentUnitPointSum;
import com.school.academic.repository.data.UnitDetailData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UnitStudentService {

    private final UnitStudentRepository repository;
    private final UnitStudentMapper mapper;

    //////////////
    // CRUD
    /////////////

    public UnitStudentDTO register(UnitStudentRegistrationDTO registrationDTO) {
        UnitStudent entity = mapper.toEntity(registrationDTO);
        entity = this.save(entity);
        return mapper.toDTO(entity);
    }

    public UnitStudent save(UnitStudent entity) {
        repository.save(entity);
        log.debug("Saved unit: {}, for Student: {}", entity.getUnitId(), entity.getStudentId());
        return entity;
    }

    public List<UnitStudent> findByStudentId(Long studentId) {
        return repository.findAllByStudentId(studentId);
    }

    ///////////
    // OTHER
    //////////

    public BigDecimal getStudentUnitPointSum(Long studentId) {
        log.debug("Request to get Student: {}, unit point sum", studentId);
        BigDecimal pointSum = BigDecimal.ZERO;

        StudentUnitPointSum result = repository.getStudentUnitPointSum(studentId);
        if (result != null && result.getSum() != null) {
            pointSum = result.getSum();
        }

        log.debug("Student: {}, point sum: {}", studentId, pointSum);
        return pointSum;
    }

    public List<Long> getStudentUnits(Long studentId) {
        log.debug("Request to get Student: {}, units", studentId);

        List<UnitStudent> unitStudents = this.findByStudentId(studentId);

        List<Long> returnValue = unitStudents.stream()
                .map(UnitStudent::getUnitId)
                .collect(Collectors.toList());
        log.debug("Student: {}, units: {}", studentId, returnValue);
        return returnValue;
    }

    public List<UnitStudentDetail> getStudentUnitDetails(Long studentId) {
        log.debug("Request to get student unit details with studentId: {}", studentId);
        List<UnitDetailData> result = repository.findUnitByStudentId(studentId);

        return result.stream()
                .map(mapper::toUnitDetails)
                .collect(Collectors.toList());
    }
}
