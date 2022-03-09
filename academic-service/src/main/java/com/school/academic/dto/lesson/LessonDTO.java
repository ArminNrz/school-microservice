package com.school.academic.dto.lesson;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LessonDTO extends LessonBaseDTO {
    private Long id;
}
