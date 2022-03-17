package com.school.academic.service.entity;

import com.school.academic.domain.Student;
import com.school.academic.dto.student.StudentCreateDTO;
import com.school.academic.dto.student.StudentDTO;
import com.school.academic.mapper.StudentMapper;
import com.school.academic.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

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

    public void update(Student entity) {
        log.debug("Try to update student: {}", entity);
        repository.save(entity);
        log.debug("Updated Student: {}", entity);
    }

    public Student getByNationalCode(Long nationalCode) {

        Optional<Student> studentOptional = repository.findByNationalCode(nationalCode);
        if (studentOptional.isEmpty()) {
            log.error("No such student exist with nationalCode: {}", nationalCode);
            return null;
        }

        return studentOptional.get();
    }

    public Student findById(Long id) {
        log.debug("Request to get student by id: {}", id);
        Optional<Student> studentOptional = repository.findById(id);
        if (studentOptional.isEmpty()) {
            log.error("No such Student exist with id: {}", id);
            return null;
        }

        Student foundStudent = studentOptional.get();
        log.debug("Found by id: {}, Student: {}", id, foundStudent);
        return foundStudent;
    }

    public void endRegistration(Long id) {
        log.debug("Request to end register for Student: {}", id);

        Student foundStudent = this.findById(id);
        if (foundStudent == null) {
            throw Problem.valueOf(Status.NOT_FOUND, "No such Student exist with this id");
        }

        foundStudent.setAccessUnitRegistration(false);
        this.update(foundStudent);

        log.debug("End registration for Student: {}", id);
    }
}
