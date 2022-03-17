package com.school.academic.controller;

import com.school.academic.dto.student.StudentCreateDTO;
import com.school.academic.dto.student.StudentDTO;
import com.school.academic.dto.student.StudentDetailsDTO;
import com.school.academic.dto.unit.student.UnitStudentDTO;
import com.school.academic.dto.unit.student.UnitStudentRegistrationDTO;
import com.school.academic.service.entity.StudentService;
import com.school.academic.service.higlevel.StudentUnitManagementService;
import com.school.clients.finance.dto.StudentFinanceRegisterResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/academic/students")
public class StudentController {

    private final StudentService service;
    private final StudentUnitManagementService studentUnitManagementService;

    @PostMapping
    public ResponseEntity<StudentDTO> create(@Valid @RequestBody StudentCreateDTO createDTO) {
        log.info("REST request to create Student: {}", createDTO);
        StudentDTO result = service.create(createDTO);
        return ResponseEntity.created(URI.create("/api/academic/students")).body(result);
    }

    //todo: update student names

    @PutMapping("/{id}/unit")
    public ResponseEntity<UnitStudentDTO> register(
            @PathVariable("id") Long studentId,
            @Valid @RequestBody UnitStudentRegistrationDTO registrationDTO) {
        log.info("REST request to register unit: {}, for StudentId: {}", registrationDTO, studentId);
        UnitStudentDTO result = studentUnitManagementService.register(studentId, registrationDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{nationalCode}")
    public ResponseEntity<StudentDetailsDTO> getDetailsByNationalCode(@PathVariable("nationalCode") Long nationalCode) {
        log.info("REST request to get student: {}, details", nationalCode);
        return ResponseEntity.ok(studentUnitManagementService.getDetailsByNationalCode(nationalCode));
    }

    //todo: end getting unit (In this API want do not let student to register any unit and after send request to finance service to register student invoice) PUT /{id}/end-register
    @PutMapping("/{id}/end-register")
    public ResponseEntity<StudentFinanceRegisterResponse> endRegisteration(@PathVariable(name = "id") Long id) {
        log.info("REST request to end register for Student: {}", id);
        StudentFinanceRegisterResponse result = studentUnitManagementService.endRegisterAndGetFinanceCode(id);
        return ResponseEntity.ok(result);
    }
}
