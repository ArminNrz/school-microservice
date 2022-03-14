package com.school.academic.dto.unit.lesson;


import com.school.academic.domain.Unit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UnitLessonDTO extends UnitLessonBaseDTO {

    List<Unit> units;

}
