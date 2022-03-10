package com.school.academic.mapper;

import com.school.academic.domain.Student;
import com.school.academic.domain.Unit;
import com.school.academic.domain.UnitStudent;
import com.school.academic.dto.unit.student.UnitStudentDTO;
import com.school.academic.dto.unit.student.UnitStudentRegistrationDTO;
import org.springframework.stereotype.Component;

@Component
public class UnitStudentMapper {

    public UnitStudent toEntity(UnitStudentRegistrationDTO dto) {
        UnitStudent entity = new UnitStudent();

        Student student = new Student();
        student.setId(dto.getStudentId());
        entity.setStudent(student);
        entity.setStudentId(dto.getStudentId());

        Unit unit = new Unit();
        unit.setId(dto.getUnitId());
        entity.setUnit(unit);
        entity.setUnitId(dto.getUnitId());

        return entity;
    }

    public UnitStudentDTO toDTO(UnitStudent entity) {
        UnitStudentDTO dto = new UnitStudentDTO();
        dto.setStudentId(entity.getStudentId());
        dto.setUnitId(entity.getUnitId());
        return dto;
    }
}
