package com.school.academic.mapper;

import com.school.academic.domain.Lesson;
import com.school.academic.dto.lesson.LessonCreateDTO;
import com.school.academic.dto.lesson.LessonDTO;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {

    public Lesson toEntity(LessonCreateDTO dto) {
        Lesson entity = new Lesson();
        entity.setName(dto.getName());
        return entity;
    }

    public LessonDTO toDTO(Lesson entity) {
        LessonDTO dto = new LessonDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
}
