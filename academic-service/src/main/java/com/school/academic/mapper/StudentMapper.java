package com.school.academic.mapper;

import com.school.academic.domain.Student;
import com.school.academic.dto.student.StudentCreateDTO;
import com.school.academic.dto.student.StudentDTO;
import com.school.academic.dto.student.StudentDetailsDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentCreateDTO dto) {
        Student entity = new Student();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setNationalCode(dto.getNationalCode());
        return entity;
    }

    public StudentDTO toDTO(Student entity) {
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setNationalCode(entity.getNationalCode());
        return dto;
    }

    public StudentDetailsDTO toDetailsDTO(Student entity) {
        StudentDetailsDTO dto = new StudentDetailsDTO();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setNationalCode(entity.getNationalCode());
        return dto;
    }
}
