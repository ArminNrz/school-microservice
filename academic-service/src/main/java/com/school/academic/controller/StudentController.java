package com.school.academic.controller;

import com.school.academic.domain.Student;
import com.school.academic.dto.student.StudentCreateDTO;
import com.school.academic.dto.student.StudentDetailsDTO;
import com.school.academic.dto.student.StudentFactorDTO;
import com.school.academic.dto.unit.student.UnitStudentDTO;
import com.school.academic.dto.unit.student.UnitStudentRegistrationDTO;
import com.school.academic.service.entity.StudentService;
import com.school.academic.service.higlevel.student.StudentUnitManagementService;
import com.school.clients.academic.StudentDTO;
import com.school.clients.finance.dto.StudentFinanceRegisterResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/academic/students")
public class StudentController {

    private final StudentService service;
    private final StudentUnitManagementService studentUnitManagementService;

    @Secured("ROLE_admin")
    @PostMapping
    public ResponseEntity<StudentDTO> create(@Valid @RequestBody StudentCreateDTO createDTO) {
        log.info("REST request to create Student: {}", createDTO);
        StudentDTO result = service.create(createDTO);
        return ResponseEntity.created(URI.create("/api/academic/students")).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_user', 'ROLE_admin')")
    @PutMapping("/{id}/unit")
    public ResponseEntity<UnitStudentDTO> register(
            @PathVariable("id") Long studentId,
            @Valid @RequestBody UnitStudentRegistrationDTO registrationDTO) {
        log.info("REST request to register unit: {}, for StudentId: {}", registrationDTO, studentId);
        UnitStudentDTO result = studentUnitManagementService.register(studentId, registrationDTO);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_user', 'ROLE_admin')")
    @GetMapping("/{nationalCode}")
    public ResponseEntity<StudentDetailsDTO> getDetailsByNationalCode(@PathVariable("nationalCode") Long nationalCode) {
        log.info("REST request to get student: {}, details", nationalCode);
        return ResponseEntity.ok(studentUnitManagementService.getDetailsByNationalCode(nationalCode));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_admin')")
    @PutMapping("/{id}/end-register")
    public ResponseEntity<StudentFinanceRegisterResponse> endRegisteration(@PathVariable(name = "id") Long id) {
        log.info("REST request to end register for Student: {}", id);
        StudentFinanceRegisterResponse result = studentUnitManagementService.endRegisterAndGetFinanceCode(id);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_user', 'ROLE_admin')")
    @GetMapping("{nationalCode}/get-factor")
    public ResponseEntity<StudentFactorDTO> getFactor (@PathVariable("nationalCode") Long nationalCode) {
        log.info("Rest Request to get factor by nationalCode: {}" , nationalCode);
        StudentFactorDTO factor = studentUnitManagementService.getFactor(nationalCode) ;
        return ResponseEntity.ok(factor) ;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_user', 'ROLE_admin')")
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getByAccessRegister(
            @Spec(path = "accessUnitRegistration", spec = Equal.class) Specification<Student> studentSpecification,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable
            ) {
        log.info("REST request to get students, with: specification: {}, pageable: {}", studentSpecification, pageable);
        return ResponseEntity.ok().body(service.getAll(studentSpecification, pageable));
    }
}
