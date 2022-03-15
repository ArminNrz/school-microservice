package com.school.academic.controller;

import com.school.academic.dto.student.StudentCreateDTO;
import com.school.academic.dto.student.StudentDTO;
import com.school.academic.dto.student.StudentDetailDTO;
import com.school.academic.dto.unit.student.UnitStudentDTO;
import com.school.academic.dto.unit.student.UnitStudentRegistrationDTO;
import com.school.academic.service.entity.StudentService;
import com.school.academic.service.higlevel.StudentRegisterUnitManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    //todo: get student by id (id, name, family, List<units = unitId, teacherName, lessonName, point>, pointSum)

    @GetMapping("/getDetails/{nationalCode}")
    public ResponseEntity<StudentDetailDTO> getStudentDetailsInfo(@PathVariable Long nationalCode) {
        log.info("REST request to getStudentDetailsInfo: {}", nationalCode);
        StudentDetailDTO studentDetails = registerUnitManager.getStudentDetails(nationalCode);
        return ResponseEntity.status(HttpStatus.OK).body(studentDetails);
    }

    /*

    GET: /{id}
    {
        firstName:
        lastName:
        nationalCode:
        unitDetails: [
            {
                unitId:
                teacherName:
                lessonName:
                pint:
            },
            {
                unitId:
                teacherName:
                lessonName:
                pint:
            }
        ]
        unitPointSum:
    }
     */


    //todo: end getting unit (In this API want do not let student to register any unit and after send request to finance service to register student invoice) PUT /{id}/end-register
}
