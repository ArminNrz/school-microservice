package com.school.finance.service;

import com.school.clients.finance.dto.StudentFactorResponse;
import com.school.clients.finance.dto.StudentFinanceRegisterRequest;
import com.school.clients.finance.dto.StudentFinanceRegisterResponse;
import com.school.finance.domain.StudentFinance;
import com.school.finance.dto.StudentFinanceDTO;
import com.school.finance.mapper.StudentFinanceMapper;
import com.school.finance.repository.StudentFinanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
        BigDecimal pointCostBigDecimal = BigDecimal.valueOf(pointCost);
        BigDecimal cost = registerRequest.getPointSum().multiply(pointCostBigDecimal);
        entity.setCost(cost);
        repository.insert(entity);
        log.debug("Registered Student invoice: {}", entity);
        return mapper.toResponse(entity);
    }

    public StudentFactorResponse getFactorByStudentId (Long studentId) {
        Optional<StudentFinance> entity = repository.findByStudentId(studentId) ;
        if(entity.isEmpty()) {
            throw Problem.valueOf(Status.NOT_FOUND , "The factor Not found") ;
        }
        return mapper.ToFactorResponse(entity.get()) ;
    }
    public StudentFinanceDTO getFactor(Long studentId) {
        log.debug("Request to get factor by studentId : {}" ,studentId );
        Optional<StudentFinance> studentFinance = repository.findByStudentId(studentId) ;

        if(studentFinance.isEmpty()) {
            throw Problem.valueOf(Status.NOT_FOUND , "Factor by this student Id not found ...") ;
        }
        return mapper.toDTO(studentFinance.get()) ;

    }
    public StudentFinance updateFactor(StudentFinanceDTO studentFinanceDTO) {
        log.debug("Request to update Factor ...");
        StudentFinance studentFinance = mapper.toFinanceEntity(studentFinanceDTO) ;
        return repository.save(studentFinance) ;

    }


}
