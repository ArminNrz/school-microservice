package com.school.finance.service;

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
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public StudentFinance payFactor(Long studentId , BigDecimal amount) {
        log.debug("Request to pay Factor with studentId : {}" , studentId);
        Optional<StudentFinance> OptionalStudentFinance = repository.findByStudentId(studentId) ;
        if(OptionalStudentFinance.isEmpty()) {
            throw Problem.valueOf(Status.NOT_FOUND , "No factor found ...") ;

        }
        if(amount.equals(BigDecimal.ZERO)) {
            throw Problem.valueOf(Status.FORBIDDEN , "Amount can't be ZERO ! ") ;
        }
        StudentFinance studentFinance = OptionalStudentFinance.get() ;
        BigDecimal cost = studentFinance.getCost() ;
        BigDecimal updatedCost ;
        log.debug("comparing Factor cost  and payment amount :  , {} , : {} ",cost,amount);
        if(cost.compareTo(amount) > 0 ) {
            updatedCost = cost.subtract(amount) ;
            studentFinance.setCost(updatedCost);
            repository.save(studentFinance) ;
        }
        if( cost.compareTo(amount) <= 0 ) {
            updatedCost = BigDecimal.ZERO ;
            studentFinance.setCost(updatedCost);
            studentFinance.setIsPaid(true);
            repository.save(studentFinance) ;
        }

        return studentFinance ;
    }

}
