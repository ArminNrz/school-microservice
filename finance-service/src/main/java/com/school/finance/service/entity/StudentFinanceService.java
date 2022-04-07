package com.school.finance.service.entity;

import com.school.clients.finance.dto.StudentFactorResponse;
import com.school.clients.finance.dto.StudentFinanceRegisterRequest;
import com.school.clients.finance.dto.StudentFinanceRegisterResponse;
import com.school.finance.domain.StudentFinance;
import com.school.finance.mapper.StudentFinanceMapper;
import com.school.finance.repository.StudentFinanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentFinanceService {

    @Value("${finance.point-cost}")
    private Long pointCost;

    private final StudentFinanceRepository repository;
    private final StudentFinanceMapper mapper;

    public StudentFinanceRegisterResponse register(StudentFinanceRegisterRequest registerRequest) {
        StudentFinance entity = mapper.toEntity(registerRequest);

        /*
        calculate cost
         */
        BigDecimal pointCostBigDecimal = BigDecimal.valueOf(pointCost);
        BigDecimal cost = registerRequest.getPointSum().multiply(pointCostBigDecimal);
        entity.setCost(cost);

        repository.insert(entity);
        log.debug("Registered Student invoice: {}", entity);
        return mapper.toResponse(entity);
    }

    public StudentFactorResponse getFactorByStudentId(Long studentId) {
        Optional<StudentFinance> entity = this.getByStudentId(studentId);
        if(entity.isEmpty()) {
            throw Problem.valueOf(Status.NOT_FOUND , "The factor Not found") ;
        }
        return mapper.ToFactorResponse(entity.get()) ;
    }

    public Optional<StudentFinance> getByStudentId(Long studentId) {
        log.debug("Request to get factor by studentId : {}" ,studentId );
        Optional<StudentFinance> studentFinance = repository.findByStudentId(studentId);
        log.debug("Found for Student: {}, factor: {}", studentId, studentFinance);
        return studentFinance;
    }

    public void updateFactor(StudentFinance studentFinance) {
        log.debug("Request to update StudentFinance: {}", studentFinance);
        repository.save(studentFinance);
        log.debug("Updated StudentFinance: {}", studentFinance);
    }

}
