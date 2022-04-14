package com.school.finance.controller;

import com.school.clients.finance.dto.StudentFactorResponse;
import com.school.clients.finance.dto.StudentFinanceRegisterRequest;
import com.school.clients.finance.dto.StudentFinanceRegisterResponse;
import com.school.finance.dto.StudentFinanceDTO;
import com.school.finance.dto.student.StudentFinancePaymentDTO;
import com.school.finance.service.entity.StudentFinanceService;
import com.school.finance.service.highLevel.PaymentServiceHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/finance/students")
public class StudentFinanceController {

    private final StudentFinanceService service;
    private final PaymentServiceHandler paymentServiceHandler;

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
    public ResponseEntity<StudentFinanceDTO> payFactorByStudentId(
            @PathVariable("studentId") Long studentId,
            @RequestBody StudentFinancePaymentDTO paymentDTO
    ) {
        log.info("Rest Request to get factor By studentId : {}" , studentId);
        paymentDTO.setStudentId(studentId);
        StudentFinanceDTO updatedFactor = paymentServiceHandler.payment(paymentDTO) ;
        return ResponseEntity.ok(updatedFactor) ;
    }
    @GetMapping("/paid-factors")
    public ResponseEntity<List<StudentFinanceRegisterResponse>> getPaidFactors() {
        log.info("Rest request to get Paid factors .");
        return ResponseEntity.ok(service.getFactorsByStatus(true)) ;
    }
}
