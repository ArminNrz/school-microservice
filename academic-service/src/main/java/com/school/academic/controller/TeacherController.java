package com.school.academic.controller;

import com.school.academic.dto.teacher.TeacherCreateDTO;
import com.school.academic.dto.teacher.TeacherDTO;
import com.school.academic.dto.unit.teacher.UnitTeacherDTO;
import com.school.academic.dto.unit.teacher.UnitTeacherRegistrationDTO;
import com.school.academic.service.entity.TeacherService;
import com.school.academic.service.entity.UnitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/academic/teachers")
public class TeacherController {

    private final TeacherService service;
    private final UnitService unitService;

    @PostMapping
    public ResponseEntity<TeacherDTO> create(@Valid @RequestBody TeacherCreateDTO createDTO) {
        log.info("REST request to create Teacher: {}", createDTO);
        TeacherDTO result = service.create(createDTO);
        return ResponseEntity.created(URI.create("/api/academic/teachers")).body(result);
    }


    @PutMapping("/{id}/unit")
    public ResponseEntity<UnitTeacherDTO> registerUnit(
            @PathVariable("id") Long teacherId,
            @Valid @RequestBody UnitTeacherRegistrationDTO registrationDTO
    ) {
        log.info("REST request to register Unit: {}, for Teacher with id: {}", registrationDTO, teacherId);
        UnitTeacherDTO result = unitService.register(teacherId, registrationDTO);
        return ResponseEntity.ok(result);
    }
}
