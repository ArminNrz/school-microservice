package com.school.academic.service.entity;

import com.school.academic.domain.Lesson;
import com.school.academic.dto.lesson.LessonCreateDTO;
import com.school.academic.dto.lesson.LessonDTO;
import com.school.academic.mapper.LessonMapper;
import com.school.academic.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository repository;
    private final LessonMapper mapper;

    public LessonDTO create(LessonCreateDTO createDTO) {
        Lesson entity = mapper.toEntity(createDTO);
        repository.save(entity);
        log.debug("Saved, Lesson: {}", entity);
        return mapper.toDTO(entity);
    }

    public List<LessonDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Lesson> lessonPage = repository.findAll(pageable);

        return lessonPage.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public LessonDTO getLessonById(Long lessonId) {
        log.debug("Enter to get lesson By Id : {}", lessonId);
        Lesson lesson = repository.getById(lessonId);
        LessonDTO lessonDTO = mapper.toDTO(lesson);

        return lessonDTO;
    }
}
