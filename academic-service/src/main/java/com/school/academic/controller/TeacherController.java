package com.school.academic.controller;

import com.school.academic.dto.teacher.TeacherCreateDTO;
import com.school.academic.dto.teacher.TeacherDTO;
import com.school.academic.dto.teacher.TeacherDetailsDTO;
import com.school.academic.dto.unit.teacher.UnitTeacherDTO;
import com.school.academic.dto.unit.teacher.UnitTeacherRegistrationDTO;
import com.school.academic.service.entity.TeacherService;
import com.school.academic.service.entity.UnitService;
import com.school.academic.service.higlevel.teacher.TeacherUnitManagementService;
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
    private final TeacherUnitManagementService teacherUnitManagementService;

    @PostMapping
    public ResponseEntity<TeacherDTO> create(@Valid @RequestBody TeacherCreateDTO createDTO) {
        log.info("REST request to create Teacher: {}", createDTO);
        TeacherDTO result = service.create(createDTO);
        return ResponseEntity.created(URI.create("/api/academic/teachers")).body(result);
    }

    //todo: update teacher (name)

    @PutMapping("/{id}/unit")
    public ResponseEntity<UnitTeacherDTO> registerUnit(
            @PathVariable("id") Long teacherId,
            @Valid @RequestBody UnitTeacherRegistrationDTO registrationDTO
    ) {
        log.info("REST request to register Unit: {}, for Teacher with id: {}", registrationDTO, teacherId);
        UnitTeacherDTO result = unitService.register(teacherId, registrationDTO);
        return ResponseEntity.ok(result);
    }

    //todo: get unit of a teacher
    @GetMapping("/{nationalCode}")
    public  ResponseEntity<TeacherDetailsDTO> getDetailsByNationalCode(@PathVariable(name ="nationalCode")Long nationalCode){
        log.info("REST request to get teacher: {}, details", nationalCode);
        TeacherDetailsDTO result = teacherUnitManagementService.getDetailsByNationalCode(nationalCode);
        return ResponseEntity.ok(result);
    }


    //todo: get all units with page and size
}
