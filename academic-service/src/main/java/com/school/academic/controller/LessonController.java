package com.school.academic.controller;

import com.school.academic.dto.lesson.LessonCreateDTO;
import com.school.academic.dto.lesson.LessonDTO;
import com.school.academic.service.entity.LessonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/academic/lessons")
public class LessonController {

    private final LessonService service;

    @PostMapping
    public ResponseEntity<LessonDTO> create(@Valid @RequestBody LessonCreateDTO createDTO) {
        log.info("REST request to create Lesson: {}", createDTO);
        LessonDTO result = service.create(createDTO);
        return ResponseEntity.created(URI.create("/api/academic/lessons")).body(result);
    }

    @GetMapping
    public ResponseEntity<List<LessonDTO>> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        log.info("REST request to get all Lessons, page: {}, size: {}", page, size);
        List<LessonDTO> results = service.getAll(page, size);
        return ResponseEntity.ok(results);
    }
}
