package com.example.hucodeuz.repository;

import com.example.hucodeuz.entity.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author "Husniddin Ulachov"
 * @created 2:50 PM on 6/26/2022
 * @project Edu-Center
 */
public interface GroupRepository extends JpaRepository<Group,Long> {

      boolean existsByNameAndFilial_IdAndCourse_Id(String name, Long filialId, Long courseId);
      List<Group> findAllByNameContainingIgnoreCase(String name);
      Page<Group> findAllByFilial_NameContainingIgnoreCaseAndCourse_NameContainingIgnoreCaseAndNameContainingIgnoreCase(String filial, String course, String name, Pageable pageable);


}
