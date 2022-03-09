package com.school.academic.mapper;

import com.school.academic.domain.Lesson;
import com.school.academic.domain.Teacher;
import com.school.academic.domain.Unit;
import com.school.academic.dto.unit.UnitDTO;
import com.school.academic.dto.unit.UnitRegistrationDTO;
import org.springframework.stereotype.Component;

@Component
public class UnitMapper {

    public Unit toEntity(UnitRegistrationDTO dto) {
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

    public UnitDTO toDTO(Unit entity) {
        UnitDTO dto = new UnitDTO();
        dto.setId(entity.getId());
        dto.setTeacherId(entity.getTeacherId());
        dto.setLessonId(entity.getLessonId());
        dto.setPoint(entity.getPoint());
        return dto;
    }
}
