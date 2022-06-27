package com.example.hucodeuz.service;

import com.example.hucodeuz.dto.ApiResponse;
import com.example.hucodeuz.dto.GroupDto;
import com.example.hucodeuz.entity.Course;
import com.example.hucodeuz.entity.Filial;
import com.example.hucodeuz.entity.Group;
import com.example.hucodeuz.exception.ResourceNotFoundException;
import com.example.hucodeuz.repository.CourseRepository;
import com.example.hucodeuz.repository.FilialRepository;
import com.example.hucodeuz.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author "Husniddin Ulachov"
 * @created 2:57 PM on 6/26/2022
 * @project Edu-Center
 */
@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    private final CourseRepository courseRepository;

    private final FilialRepository filialRepository;

    public ApiResponse<Group> save(GroupDto groupDto) {
        Optional<Course> courseOptional = courseRepository.findById(groupDto.getCourseId());
        if (courseOptional.isEmpty()) {
            return new ApiResponse<>(new ResourceNotFoundException("course", "Id", groupDto.getCourseId()).getMessage(), null, false);
        }
        Course course = courseOptional.get();
        Optional<Filial> optional = filialRepository.findById(groupDto.getFilialId());
        if (optional.isEmpty())
            return new ApiResponse<>(new ResourceNotFoundException("filial", "Id", groupDto.getFilialId()).getMessage(), null, false);
        Filial filial = optional.get();

        Group group = new Group();
        group.setCourse(course);
        group.setFilial(filial);
        if (groupRepository.existsByNameAndFilial_IdAndCourse_Id(
                groupDto.getName(),
                groupDto.getFilialId(),
                groupDto.getCourseId())) {
            return new ApiResponse<>(  new  RuntimeException("Bunday guruh mavjud!!!").getMessage(), null, false);
        }
        group.setName(groupDto.getName());
        return new ApiResponse<>("saved", groupRepository.save(group), true);
    }
}
