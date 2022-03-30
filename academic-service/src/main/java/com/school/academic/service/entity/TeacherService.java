package com.school.academic.service.entity;

import com.school.academic.domain.Teacher;
import com.school.academic.dto.teacher.TeacherCreateDTO;
import com.school.academic.dto.teacher.TeacherDTO;
import com.school.academic.mapper.TeacherMapper;
import com.school.academic.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository repository;
    private final TeacherMapper mapper;

    public TeacherDTO create(TeacherCreateDTO createDTO) {
        Teacher entity = mapper.toEntity(createDTO);
        repository.save(entity);
        return mapper.toDTO(entity);
    }

    public Teacher getByNationalCode(Long nationalCode) {
        Optional<Teacher> teacherOptional=repository.findByNationalCode(nationalCode);
        if (teacherOptional.isEmpty()) {
            log.error("No such teacher exist with nationalCode: {}", nationalCode);
            return null;
        }
        return  teacherOptional.get();
    }
}
