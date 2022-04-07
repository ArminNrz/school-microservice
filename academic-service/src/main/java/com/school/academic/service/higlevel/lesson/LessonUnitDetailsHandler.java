package com.school.academic.service.higlevel.lesson;

import com.school.academic.domain.Lesson;
import com.school.academic.domain.Unit;
import com.school.academic.dto.lesson.LessonDTO;
import com.school.academic.dto.unit.lesson.UnitLessonDTO;
import com.school.academic.dto.unit.student.UnitDetailsDTO;
import com.school.academic.mapper.LessonMapper;
import com.school.academic.mapper.UnitMapper;
import com.school.academic.service.entity.LessonService;
import com.school.academic.service.entity.UnitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class LessonUnitDetailsHandler {
    private final UnitService unitService;
    private final LessonService lessonService;
    private final UnitMapper unitMapper;
    private final LessonMapper lessonMapper;


    public UnitLessonDTO getUnitsByLessonId(Long lessonId) {
        log.debug("Request to get units lesson with lessonId: {}", lessonId);

        UnitLessonDTO unitLessonDTO = new UnitLessonDTO();

        Lesson lesson = lessonService.getById(lessonId);
        LessonDTO lessonDTO = lessonMapper.toDTO(lesson);
        unitLessonDTO.setLessonDTO(lessonDTO);

        List<Unit> units = unitService.getAllUnitsByLessonId(lessonId);
        List<UnitDetailsDTO> unitDetailsDTOList = units.stream().map(unitMapper::toUnitDetailsDTO).collect(Collectors.toList());
        unitLessonDTO.setLessonUnits(unitDetailsDTOList);

        log.debug("units: {}", units);

        return unitLessonDTO;
    }
}
