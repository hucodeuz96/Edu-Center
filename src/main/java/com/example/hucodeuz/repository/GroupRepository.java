package com.example.hucodeuz.repository;

import com.example.hucodeuz.dto.GroupDto;
import com.example.hucodeuz.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author "Husniddin Ulachov"
 * @created 2:50 PM on 6/26/2022
 * @project Edu-Center
 */
public interface GroupRepository extends JpaRepository<Group,Long> {

      boolean existsByNameAndFilial_IdAndCourse_Id(String name, Long filialId, Long courseId);
}
