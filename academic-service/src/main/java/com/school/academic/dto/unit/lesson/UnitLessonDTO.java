package com.school.academic.dto.unit.lesson;

import com.school.academic.dto.lesson.LessonDTO;
import com.school.academic.dto.unit.student.UnitDetailsDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode
@Data
public class UnitLessonDTO {
    LessonDTO lessonDTO;
    List<UnitDetailsDTO> lessonUnits;
}
