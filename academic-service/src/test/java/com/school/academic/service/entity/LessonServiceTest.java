package com.school.academic.service.entity;

import com.school.academic.domain.Lesson;
import com.school.academic.dto.lesson.LessonCreateDTO;
import com.school.academic.dto.lesson.LessonDTO;
import com.school.academic.mapper.LessonMapper;
import com.school.academic.repository.LessonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LessonServiceTest {

    @Mock
    private LessonRepository lessonRepository;

    @Mock
    private LessonMapper lessonMapper;

    @InjectMocks
    private LessonService lessonService;

    @Test
    void create() {

        LessonCreateDTO createDTO = new LessonCreateDTO();
        createDTO.setName("TestLessonName");

        Lesson lesson = new Lesson();
        lesson.setName(createDTO.getName());
        Mockito.when(lessonMapper.toEntity(createDTO))
                        .thenReturn(lesson);

        lesson.setId(1L);
        Mockito.when(lessonRepository.save(lesson))
                        .thenReturn(lesson);

        LessonDTO dto = new LessonDTO();
        dto.setId(lesson.getId());
        dto.setName(lesson.getName());
        Mockito.when(lessonMapper.toDTO(lesson))
                        .thenReturn(dto);

        LessonCreateDTO lessonCreateDTO = new LessonCreateDTO();
        lessonCreateDTO.setName("TestLessonName");
        LessonDTO expectedResult = lessonService.create(lessonCreateDTO);

        Assertions.assertEquals(expectedResult, dto);
    }
}