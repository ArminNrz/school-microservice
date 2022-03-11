package com.school.academic.service.highlevel;


import com.school.academic.domain.Unit;
import com.school.academic.dto.lesson.LessonDTO;
import com.school.academic.dto.unit.lesson.UnitLessonDTO;
import com.school.academic.repository.UnitRepository;
import com.school.academic.service.entity.LessonService;
import com.school.academic.service.entity.UnitService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
@Data
public class LessonGetUnitManager {

    private final UnitService unitService ;
    private final LessonService lessonService ;

    public UnitLessonDTO getUnitsByLessonId(Long lessonId) {

        BigDecimal sumPoints = unitService.countUnitsByLessonId(lessonId) ;
        List<Unit> units = unitService.getUnitsByLessonId(lessonId) ;
        LessonDTO lessonDTO = lessonService.getLessonById(lessonId) ;

        UnitLessonDTO unitLessonDTO = new UnitLessonDTO() ;
        unitLessonDTO.setLessonId(lessonId);
        unitLessonDTO.setLessonName(lessonDTO.getName());
        unitLessonDTO.setUnits(units);
        unitLessonDTO.setSumPoints(sumPoints);


        return unitLessonDTO ;


    }

}
