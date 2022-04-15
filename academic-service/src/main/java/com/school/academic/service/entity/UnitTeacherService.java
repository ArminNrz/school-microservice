package com.school.academic.service.entity;

import com.school.academic.dto.unit.teacher.UnitTeacherDetailsDTO;
import com.school.academic.mapper.TeacherMapper;
import com.school.academic.repository.UnitRepository;
import com.school.academic.repository.data.UnitTeacherData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UnitTeacherService {
    private final UnitRepository repository;
    private final TeacherMapper mapper;
    public List<UnitTeacherDetailsDTO> getTeacherUnitDetails(Long teacherId) {
        log.debug("Request to get teacher unit details with studentId: {}", teacherId);
        List<UnitTeacherData> result = repository.findUnitByTeacherId(teacherId);
        return result.stream()
                .map(mapper::toUnitDetails)
                .collect(Collectors.toList());
    }
}