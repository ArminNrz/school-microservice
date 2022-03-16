package com.school.academic.controller;

import com.school.academic.dto.student.StudentCreateDTO;
import com.school.academic.dto.student.StudentDTO;
import com.school.academic.dto.unit.student.UnitStudentDTO;
import com.school.academic.dto.unit.student.UnitStudentRegistrationDTO;
import com.school.academic.service.entity.StudentService;
import com.school.academic.service.higlevel.StudentRegisterUnitManager;
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
    private final StudentRegisterUnitManager registerUnitManager;

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
        UnitStudentDTO result = registerUnitManager.register(studentId, registrationDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/financialInvoice/{studentId}")
    public ResponseEntity<StudentFinanceRegisterResponse> getFinancialInvoiceByStudentId(@PathVariable Long studentId) {
        log.info("REST request was sent to showStudentFinancialInvoice with studentId : {}", studentId);
        StudentFinanceRegisterResponse result = registerUnitManager.showStudentFinancialInvoice(studentId);
        return ResponseEntity.ok(result);
    }

}
