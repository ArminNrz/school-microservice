package com.school.academic.mapper;

import com.school.academic.domain.Lesson;
import com.school.academic.domain.Teacher;
import com.school.academic.domain.Unit;
import com.school.academic.dto.unit.student.UnitDetailsDTO;
import com.school.academic.dto.unit.teacher.UnitTeacherDTO;
import com.school.academic.dto.unit.teacher.UnitTeacherRegistrationDTO;
import org.springframework.stereotype.Component;

@Component
public class UnitMapper {

    public Unit toEntity(UnitTeacherRegistrationDTO dto) {
        Unit entity = new Unit();

        Lesson lesson = new Lesson();
        lesson.setId(dto.getLessonId());
        entity.setLesson(lesson);
        entity.setLessonId(dto.getLessonId());

        Teacher teacher = new Teacher();
        teacher.setId(dto.getTeacherId());
        entity.setTeacher(teacher);
        entity.setTeacherId(dto.getTeacherId());

        entity.setPoint(dto.getPoint());
        entity.setIsActive(true);

        return entity;
    }

    public UnitTeacherDTO toDTO(Unit entity) {
        UnitTeacherDTO dto = new UnitTeacherDTO();
        dto.setId(entity.getId());
        dto.setTeacherId(entity.getTeacherId());
        dto.setLessonId(entity.getLessonId());
        dto.setPoint(entity.getPoint());
        return dto;
    }

    public UnitDetailsDTO toUnitDetailsDTO(Unit entity) {
        UnitDetailsDTO dto = new UnitDetailsDTO();
        dto.setLessonName(entity.getLesson() != null ? entity.getLesson().getName() : null);
        dto.setTeacherName(entity.getTeacher() != null ? entity.getTeacher().getName() : null);
        dto.setPoint(entity.getPoint());
        return dto;
    }
}
