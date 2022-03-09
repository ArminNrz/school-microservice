package com.school.academic.controller;

import com.school.academic.dto.teacher.TeacherCreateDTO;
import com.school.academic.dto.teacher.TeacherDTO;
import com.school.academic.dto.unit.UnitDTO;
import com.school.academic.dto.unit.UnitRegistrationDTO;
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

    //todo: update teacher (name)

    @PutMapping("/{id}/unit")
    public ResponseEntity<UnitDTO> registerUnit(
            @PathVariable("id") Long teacherId,
            @Valid @RequestBody UnitRegistrationDTO registrationDTO
    ) {
        log.info("REST request to register Unit: {}, for Teacher with id: {}", registrationDTO, teacherId);
        UnitDTO result = unitService.register(teacherId, registrationDTO);
        return ResponseEntity.ok(result);
    }

    //todo: get unit of a teacher

    //todo: get unit of a lesson

    //todo: get all units with page and size
}
