package com.school.finance.service.entity;

import com.school.clients.academic.StudentDTO;
import com.school.clients.finance.dto.StudentFactorResponse;
import com.school.clients.finance.dto.StudentFinanceRegisterRequest;
import com.school.clients.finance.dto.StudentFinanceRegisterResponse;
import com.school.finance.domain.StudentFinance;

import com.school.finance.domain.StudentPayment;
import com.school.finance.dto.student.StudentPayedDTO;

import com.school.finance.mapper.StudentFinanceMapper;
import com.school.finance.repository.StudentFinanceRepository;
import com.school.finance.service.thirdparty.AcademicClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentFinanceService {

    @Value("${finance.point-cost}")
    private Long pointCost;

    private final StudentFinanceRepository repository;
    private final MongoTemplate mongoTemplate;
    private final AcademicClientService academicClientService;
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

    public void update(StudentFinance studentFinance, StudentPayment studentPayment) {
        log.debug("Request to update Student Finance entity, studentFinance: {}, studentPayment: {}", studentFinance, studentPayment);

        if (studentFinance.getStudentPayments() == null) {
            List<StudentPayment> payments = new ArrayList<>();
            payments.add(studentPayment);
            studentFinance.setStudentPayments(payments);
        }
        else {
            studentFinance.getStudentPayments().add(studentPayment);
        }

        repository.save(studentFinance);
        log.debug("Updated studentFinance entity: {}", studentFinance);
    }
    public List<StudentFinanceRegisterResponse> getFactorsByStatus(Boolean status) {
        log.debug("Request to get paid factors with paid Status : {}" , status);
        List<StudentFinance> studentFinances = repository.findByIsPaid(status) ;
        log.debug("All factors with the status : {} are : {}",status,studentFinances);
        return studentFinances.stream().map(mapper::toResponse).collect(Collectors.toList()) ;
    }

    public List<StudentPayedDTO> getPaid(int page, int size) {
        log.debug("Request to get paid students, page: {}, size: {}", page, size);

        Query query = new Query();
        query.addCriteria(Criteria.where("isPaid").is(true)).with(PageRequest.of(page, size));
        List<StudentFinance> result = mongoTemplate.find(query, StudentFinance.class);
        log.debug("Found paid studentFinances: {}", result);

        List<Long> studentIds = result.stream().map(StudentFinance::getStudentId).collect(Collectors.toList());
        List<StudentDTO> students = academicClientService.getNotAccessRegisterStudents(0, 200)
                .stream()
                .filter(studentDTO -> studentIds.contains(studentDTO.getId()))
                .collect(Collectors.toList());


        return result.stream()
                .map(studentFinance ->  students.stream()
                            .filter(studentDTO -> studentFinance.getStudentId().equals(studentDTO.getId()))
                            .map(studentDTO -> mapper.toPaidDTO(studentFinance, studentDTO))
                            .findFirst().get())
                .collect(Collectors.toList());
    }
}
