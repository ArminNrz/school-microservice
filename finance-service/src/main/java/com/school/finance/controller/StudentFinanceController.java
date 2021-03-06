package com.school.finance.controller;

import com.school.clients.finance.dto.StudentFactorResponse;
import com.school.clients.finance.dto.StudentFinanceRegisterRequest;
import com.school.clients.finance.dto.StudentFinanceRegisterResponse;
import com.school.finance.dto.student.StudentFinanceDTO;
import com.school.finance.dto.student.StudentFinancePaymentDTO;
import com.school.finance.dto.student.StudentPayedDTO;
import com.school.finance.service.entity.StudentFinanceService;
import com.school.finance.service.highLevel.PaymentServiceManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/finance/students")
public class StudentFinanceController {

    private final StudentFinanceService service;
    private final PaymentServiceManager paymentServiceManager;

    @PreAuthorize("hasAnyAuthority('ROLE_finance-admin')")
    @PostMapping
    public ResponseEntity<StudentFinanceRegisterResponse> register(@RequestBody StudentFinanceRegisterRequest registerRequest) {
        log.info("REST request to register invoice for Student: {}", registerRequest.getStudentId());
        StudentFinanceRegisterResponse response = service.register(registerRequest);
        return ResponseEntity.created(URI.create("/api/finance/students")).body(response);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_finance-admin')")
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentFactorResponse> getStudentFactor (@PathVariable("studentId") Long studentId) {
        log.info("Rest Request to get Factor by StudentId : {}"  , studentId) ;
        StudentFactorResponse factor = service.getFactorByStudentId(studentId) ;
        return ResponseEntity.ok(factor);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_finance-user')")
    @PutMapping("/{studentId}/pay-factor")
    public ResponseEntity<StudentFinanceDTO> payFactorByStudentId(
            @PathVariable("studentId") Long studentId,
            @RequestBody StudentFinancePaymentDTO paymentDTO
    ) {
        log.info("Rest Request to get factor By studentId : {}" , studentId);
        paymentDTO.setStudentId(studentId);
        StudentFinanceDTO updatedFactor = paymentServiceManager.payment(paymentDTO) ;
        return ResponseEntity.ok(updatedFactor) ;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_finance-user')")
    @GetMapping("/paid")
    public ResponseEntity<List<StudentPayedDTO>> getPaid(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        log.info("REST request to get paid student list, page: {}, size: {}", page, size);
        List<StudentPayedDTO> result = service.getPaid(page, size);
        return ResponseEntity.ok().body(result);
    }
}
