package com.school.academic.mapper;

import com.school.academic.domain.Teacher;
import com.school.academic.dto.teacher.TeacherCreateDTO;
import com.school.academic.dto.teacher.TeacherDTO;
import com.school.academic.dto.teacher.TeacherDetailsDTO;
import com.school.academic.dto.unit.teacher.UnitTeacherDetailsDTO;
import com.school.academic.repository.data.UnitTeacherData;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {

    public Teacher toEntity(TeacherCreateDTO dto) {
        Teacher entity = new Teacher();
        entity.setName(dto.getName());
        entity.setNationalCode(dto.getNationalCode());
        return entity;
    }

    public TeacherDTO toDTO(Teacher entity) {
        TeacherDTO dto = new TeacherDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setNationalCode(entity.getNationalCode());
        return dto;
    }

    public TeacherDetailsDTO toDetailsDTO(Teacher teacher) {
        TeacherDetailsDTO dto = new TeacherDetailsDTO();
        dto.setName(teacher.getName());
        dto.setNationalCode(teacher.getNationalCode());
        return dto;

    }

    public UnitTeacherDetailsDTO toUnitDetails(UnitTeacherData unitTeacherData) {
        UnitTeacherDetailsDTO detail = new UnitTeacherDetailsDTO();
        detail.setUnitId(unitTeacherData.getId());
        detail.setLessonName(unitTeacherData.getLessonName());
        detail.setPoint(unitTeacherData.getPoint());
        return detail;

    }
}
