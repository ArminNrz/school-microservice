package com.school.academic.mapper;

import com.school.academic.domain.Teacher;
import com.school.academic.dto.teacher.TeacherCreateDTO;
import com.school.academic.dto.teacher.TeacherDTO;
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
}
