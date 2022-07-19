package com.example.hucodeuz.service;

import com.example.hucodeuz.dto.*;
import com.example.hucodeuz.entity.Course;
import com.example.hucodeuz.entity.Filial;
import com.example.hucodeuz.entity.Group;
import com.example.hucodeuz.entity.Student;
import com.example.hucodeuz.enums.StudentStatus;
import com.example.hucodeuz.exception.ResourceNotFoundException;
import com.example.hucodeuz.repository.CourseRepository;
import com.example.hucodeuz.repository.FilialRepository;
import com.example.hucodeuz.repository.GroupRepository;
import com.example.hucodeuz.repository.StudentRepository;
import com.example.hucodeuz.util.DateFormatUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final FilialRepository filialRepository;
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final DateFormatUtil dateFormatUtil;

    public ApiResponse<?> add(StudentDTO studentDTO) {
        Filial filial = filialRepository.findById(studentDTO.getFilialId()).orElseThrow(() -> new ResourceNotFoundException("filial","id",studentDTO.getFilialId()));
        List<Group> groups = groupRepository.findAllById(studentDTO.getGroupIds());
        List<Course> courses = courseRepository.findAllById(studentDTO.getCourseIds());

        Student byId=new Student();
        byId.setFilial(filial);
        byId.setFullName(studentDTO.getFullName());
        byId.setGenderType(studentDTO.getGender());
        byId.setBalance(0d);
        byId.setBirth(dateFormatUtil.stringtoDate(studentDTO.getBirth()));
        byId.setPhone(studentDTO.getPhone());
        byId.setAddress(studentDTO.getAddress());
        byId.setCardNumber(studentDTO.getCardNumber());
        byId.setPlaceOfIssue(studentDTO.getPlaceOfIssue());
        byId.setContractNumber(studentDTO.getContractNumber());
        byId.setContractDate(dateFormatUtil.stringtoDate(studentDTO.getContractDate()));
        byId.setCourse(courses);
        byId.setGroups(groups);
        byId.setStatus(StudentStatus.REGISTERED);

        Student save = studentRepository.save(byId);
        ResStudentDto resStudentDto = mapStudent(save);
        return ApiResponse.builder().data(resStudentDto).message("ADDED!").success(true).build();
    }
    public ResStudentDto mapStudent(Student student){
        ResStudentDto resStudentDto=new ResStudentDto();
        resStudentDto.setFullName(student.getFullName());
        resStudentDto.setPhone(student.getPhone());
        resStudentDto.setBalance(student.getBalance());
        resStudentDto.setStatus(student.getStatus());
        List<String> groupNames=new ArrayList<>();
        for (Group group : student.getGroups()) {
            groupNames.add(group.getName());
        }
        resStudentDto.setGroupName(groupNames);
        List<String> courseNames=new ArrayList<>();
        for (Course course : student.getCourse()) {
            courseNames.add(course.getName());
        }
        resStudentDto.setCourseName(courseNames);
        resStudentDto.setFilialName(student.getFilial().getName());
        return resStudentDto;
    }

    public ApiResponse<?> getAll(int page, int size, String search, String filialName) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> data;

        if (search.equals("") && filialName.equals("")){
            data = studentRepository.findAll(pageable);
        }
        else {
            data= studentRepository
                    .findAllByFullNameContainingIgnoreCaseAndFilial_NameContainingIgnoreCase
                            (search,filialName,pageable);
        }


        return ApiResponse.builder().data(toDTOPage(data)).success(true).message("THERE").build();
    }

    public ApiResponse<?> getOne(UUID uuid) {
        Student byId = studentRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("Student", "id", uuid));
        return ApiResponse.builder().message("THERE").success(true).data(mapStudent(byId)).build();
    }

    public ApiResponse<?> delete(UUID uuid) {
        if (!studentRepository.existsById(uuid)) return new ApiResponse<>("Student not found", false);
        studentRepository.deleteById(uuid);
        return ApiResponse.builder().message("DELETED").success(true).build();
    }

    public ApiResponse<?> edit(UUID uuid, StudentDTO studentDTO) {
        Student byId = studentRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("Student","id",uuid));

        Filial filial = filialRepository.findById(studentDTO.getFilialId()).orElseThrow(() -> new ResourceNotFoundException("filial","id",studentDTO.getFilialId()));
        List<Group> groups = groupRepository.findAllById(studentDTO.getGroupIds());
        List<Course> courses = courseRepository.findAllById(studentDTO.getCourseIds());

        byId.setFilial(filial);
        byId.setFullName(studentDTO.getFullName());
        byId.setGenderType(studentDTO.getGender());
        byId.setBalance(0d);
        byId.setBirth(dateFormatUtil.stringtoDate(studentDTO.getBirth()));
        byId.setPhone(studentDTO.getPhone());
        byId.setAddress(studentDTO.getAddress());
        byId.setCardNumber(studentDTO.getCardNumber());
        byId.setPlaceOfIssue(studentDTO.getPlaceOfIssue());
        byId.setContractNumber(studentDTO.getContractNumber());
        byId.setContractDate(dateFormatUtil.stringtoDate(studentDTO.getContractDate()));
        byId.setCourse(courses);
        byId.setGroups(groups);

        studentRepository.save(byId);

        return ApiResponse.builder().success(true).message("EDITED").build();
    }

    public Page<ResStudentDto> toDTOPage(Page<Student> students) {
        List<ResStudentDto> collect = students.stream().map(this::mapStudent).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }
}

