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

    public StudentDTO findByNationalCode(Long nationalCode) {
        log.debug("Request to get student by nationalCode :{}", nationalCode);
        Optional<Student> studentOptional = repository.findByNationalCode(nationalCode);

        if (studentOptional.isEmpty()) {
            //todo log.error
            return null;

        }


        StudentDTO studentDTO = mapper.toDTO(studentOptional.get());
        //todo:log
        return studentDTO;

    }
}
