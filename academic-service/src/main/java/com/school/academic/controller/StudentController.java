package com.school.academic.controller;

import com.school.academic.dto.student.StudentCreateDTO;
import com.school.academic.dto.student.StudentDTO;
import com.school.academic.dto.student.StudentDetailsDTO;
import com.school.academic.dto.unit.student.UnitStudentDTO;
import com.school.academic.dto.unit.student.UnitStudentRegistrationDTO;
import com.school.academic.service.entity.StudentService;
import com.school.academic.service.highlevel.StudentGetDetails;
import com.school.academic.service.highlevel.StudentRegisterUnitManager;
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
    private final StudentGetDetails studentGetDetails;

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


    @GetMapping("/{nationalCode}")
    public ResponseEntity<StudentDetailsDTO> getStudentDetails(@PathVariable("nationalCode") Long nationalCode) {
        log.info("REST request to get student Details by NationalCode : {}", nationalCode);

        StudentDetailsDTO result = studentGetDetails.getStudentDetails(nationalCode);

        return ResponseEntity.ok(result);

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
