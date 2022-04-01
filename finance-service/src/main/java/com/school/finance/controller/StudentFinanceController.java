package com.school.finance.controller;

import com.school.clients.finance.dto.StudentFactorResponse;
import com.school.clients.finance.dto.StudentFinanceRegisterRequest;
import com.school.clients.finance.dto.StudentFinanceRegisterResponse;
import com.school.finance.dto.StudentFinanceDTO;
import com.school.finance.dto.StudentPaymentDTO;
import com.school.finance.service.StudentFinanceService;
import com.school.finance.service.highLevel.StudentPaymentHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/finance/students")
public class StudentFinanceController {

    private final StudentFinanceService service;
    private final StudentPaymentHandler handler ;

    @PostMapping
    public ResponseEntity<StudentFinanceRegisterResponse> register(@RequestBody StudentFinanceRegisterRequest registerRequest) {
        log.info("REST request to register invoice for Student: {}", registerRequest.getStudentId());
        StudentFinanceRegisterResponse response = service.register(registerRequest);
        return ResponseEntity.created(URI.create("/api/finance/students")).body(response);
    }

    @GetMapping("{studentId}")
    public ResponseEntity<StudentFactorResponse> getStudentFactor (@PathVariable("studentId") Long studentId) {
        log.info("Rest Request to get Factor by StudentId : {}"  , studentId) ;
        StudentFactorResponse factor = service.getFactorByStudentId(studentId) ;
        return ResponseEntity.created(URI.create("/api/finance/students")).body(factor);
    }

    @PostMapping("{studentId}/pay")
        public ResponseEntity<StudentFinanceDTO> payFactor(@PathVariable("studentId") Long studentId,
                                                @RequestBody StudentPaymentDTO studentPaymentDTO) {

        log.info("Rest request to pay student Factor : {} , {}" , studentId , studentPaymentDTO);
        StudentFinanceDTO studentFinanceDTO = handler.payFactor(studentId,studentPaymentDTO) ;

        return ResponseEntity.ok(studentFinanceDTO) ;
        }

}
