package com.school.finance.controller;

import com.school.clients.finance.dto.StudentFactorResponse;
import com.school.clients.finance.dto.StudentFinanceRegisterRequest;
import com.school.clients.finance.dto.StudentFinanceRegisterResponse;
import com.school.finance.dto.StudentFinanceDTO;
import com.school.finance.service.StudentFinanceService;
import com.school.finance.service.highLevel.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.net.URI;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/finance/students")
public class StudentFinanceController {

    private final StudentFinanceService service;
    private final PaymentService paymentService ;

    @PostMapping
    public ResponseEntity<StudentFinanceRegisterResponse> register(@RequestBody StudentFinanceRegisterRequest registerRequest) {
        log.info("REST request to register invoice for Student: {}", registerRequest.getStudentId());
        StudentFinanceRegisterResponse response = service.register(registerRequest);
        return ResponseEntity.created(URI.create("/api/finance/students")).body(response);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentFactorResponse> getStudentFactor (@PathVariable("studentId") Long studentId) {
        log.info("Rest Request to get Factor by StudentId : {}"  , studentId) ;
        StudentFactorResponse factor = service.getFactorByStudentId(studentId) ;
        return ResponseEntity.ok(factor);
    }

    @PutMapping("/{studentId}/pay-factor")
    public ResponseEntity<StudentFinanceDTO> payFactorByStudentId(@PathVariable("studentId") Long studentId ,
    @RequestParam("amount") BigDecimal amount) {
        log.info("Rest Request to get factor By studentId : {}" , studentId);
        StudentFinanceDTO updatedFactor = paymentService.payment(studentId , amount) ;
        return ResponseEntity.ok(updatedFactor) ;
    }
}
