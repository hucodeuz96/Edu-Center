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

    public ApiResponse<Group> save(GroupDto groupDto){
        Course course = courseRepository.findById(groupDto.getCourceId()).orElseThrow(() -> new ResourceNotFoundException("course", "Id", groupDto.getCourceId()));
        Filial filial = filialRepository.findById(groupDto.getFilialId()).orElseThrow(() -> new ResourceNotFoundException("filial", "Id", groupDto.getFilialId()));
        Group group = new Group();
        group.setCourse(course);
        group.setFilial(filial);

        if (groupRepository.existsByNameAndFilial_IdAndCourse_Id(
                groupDto.getName(),
                groupDto.getFilialId(),
                groupDto.getCourceId())) {
            throw new RuntimeException("Bnday guruh mavjud!!!");
        }

        return new ApiResponse<>("saved",groupRepository.save(group),true);
    }
}
