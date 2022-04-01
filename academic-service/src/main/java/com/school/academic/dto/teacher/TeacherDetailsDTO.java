package com.school.academic.dto.teacher;

import com.school.academic.dto.unit.teacher.UnitTeacherDetailsDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class TeacherDetailsDTO extends  TeacherBaseDTO{
    private List<UnitTeacherDetailsDTO> unitTeacherDetailsDTOList;
    private BigDecimal ActiveUnitPointSum;
}
