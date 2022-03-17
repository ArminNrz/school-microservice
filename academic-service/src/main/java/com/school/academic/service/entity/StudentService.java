package com.school.academic.service.entity;

import com.school.academic.domain.Student;
import com.school.academic.dto.student.StudentCreateDTO;
import com.school.academic.dto.student.StudentDTO;
import com.school.academic.mapper.StudentMapper;
import com.school.academic.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentService {
    
    private final StudentRepository repository;
    private final StudentMapper mapper;

    public StudentDTO create(StudentCreateDTO createDTO) {
        Student entity = mapper.toEntity(createDTO);
        repository.save(entity);
        log.debug("Saved Student: {}", entity);
        return mapper.toDTO(entity);
    }

    public Student getByNationalCode(Long nationalCode) {

        Optional<Student> studentOptional = repository.findByNationalCode(nationalCode);
        if (studentOptional.isEmpty()) {
            log.error("No such student exist with nationalCode: {}", nationalCode);
            return null;
        }

        return studentOptional.get();
    }
    public StudentDTO endRegister(Long studentId) {

        Student student = repository.getById(studentId) ;
        if(!student.getEndRegistering()) {
            student.setEndRegistering(true);
            repository.save(student) ;
        }
        return mapper.toDTO(student) ;
    }
}
