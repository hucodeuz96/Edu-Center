package com.example.hucodeuz.service;

import com.example.hucodeuz.dto.ApiResponse;
import com.example.hucodeuz.dto.GroupDTO;
import com.example.hucodeuz.dto.ResGroupDTO;
import com.example.hucodeuz.entity.Course;
import com.example.hucodeuz.entity.Filial;
import com.example.hucodeuz.entity.Group;
import com.example.hucodeuz.exception.ResourceNotFoundException;
import com.example.hucodeuz.repository.CourseRepository;
import com.example.hucodeuz.repository.FilialRepository;
import com.example.hucodeuz.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final FilialRepository filialRepository;
    private final CourseRepository courseRepository;

    public ApiResponse<?> add(GroupDTO groupDTO) {
        Filial filial = filialRepository.findById(groupDTO.getFilialId()).orElseThrow(() -> new ResourceNotFoundException("filial", "id", groupDTO.getFilialId()));
        Course course = courseRepository.findById(groupDTO.getCourseId()).orElseThrow(() -> new ResourceNotFoundException("course", "id", groupDTO.getCourseId()));
        Group group = new Group();
        group.setCourse(course);
        group.setFilial(filial);
        //nomini saqlashdan oldin tekshirish shu filialda shunaqa oldin guruh ochilmaganmi?
        if (groupRepository.existsByNameAndFilial_IdAndCourse_Id(groupDTO.getName(), groupDTO.getFilialId(), groupDTO.getCourseId()))
            throw new RuntimeException("Bunday nomli guruh mavjud!!!");
        group.setName(groupDTO.getName());
        Group save = groupRepository.save(group);

        //maptoDTO
        return ApiResponse.builder().data(toDTO(save)).message("Saved").success(true).build();
    }

    public ApiResponse<Group> getOne(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Group", "id", id));
        return new ApiResponse("Mana", toDTO(group), true);
    }
    public ApiResponse<?> remove(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Group", "id", id));
        groupRepository.delete(group);


        if (!groupRepository.existsById(id)) return new ApiResponse<>("Bunday idli guruh yo'q", false);
        groupRepository.deleteById(id);
        return ApiResponse.builder().success(true).message("Deleted!").build();
    }

    public ApiResponse<?> getAll(int page, int size, String search, String filialName, String courseName) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Group> data = null;

        if (search.equals("") && filialName.equals("") && courseName.equals("")) {
            data = groupRepository.findAll(pageable);
        }
        else {
            data = groupRepository.findAllByFilial_NameContainingIgnoreCaseAndCourse_NameContainingIgnoreCaseAndNameContainingIgnoreCase(filialName, courseName, search, pageable);
        }
        return ApiResponse.builder().data(toDTOPage(data)).message("OK").success(true).build();
    }
    public ApiResponse<?> edit(Long id, GroupDTO groupDTO) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("group", "id", id));
        Filial filial = filialRepository.findById(groupDTO.getFilialId()).orElseThrow(() -> new ResourceNotFoundException("filial", "id", groupDTO.getFilialId()));
        Course course = courseRepository.findById(groupDTO.getCourseId()).orElseThrow(() -> new ResourceNotFoundException("course", "id", groupDTO.getCourseId()));

        group.setName(groupDTO.getName());
        group.setCourse(course);
        group.setFilial(filial);
        Group save = groupRepository.save(group);
        return ApiResponse.builder().success(true).message("Edited!").data(save).build();
    }


    //mapToDTO Group -> GroupDTO
    public ResGroupDTO toDTO(Group group) {
        return ResGroupDTO.builder()
                .name(group.getName())
                .courseName(group.getCourse().getName())
                .filialName(group.getFilial().getName())
                .build();
    }

    //mapTODTOList -> toDTOPAGE
    public Page<ResGroupDTO> toDTOPage(Page<Group> groups) {
        List<ResGroupDTO> collect = groups.stream().map(this::toDTO).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }
}

