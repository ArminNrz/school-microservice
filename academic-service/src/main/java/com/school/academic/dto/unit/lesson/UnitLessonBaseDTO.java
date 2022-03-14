package com.school.academic.dto.unit.lesson;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class UnitLessonBaseDTO {
    @NotNull
    private Long lessonId;
    @NotNull
    private String LessonName;
    private BigDecimal sumPoints;
}
