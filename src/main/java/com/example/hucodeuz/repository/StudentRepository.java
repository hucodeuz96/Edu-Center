package com.example.hucodeuz.repository;


import com.example.hucodeuz.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.Optional;
import java.util.UUID;

/**
 * @author "Husniddin Ulachov"
 * @created 2:50 PM on 6/26/2022
 * @project Edu-Center
 */
@RepositoryRestResource(path = "student")
public interface StudentRepository extends JpaRepository<Student, UUID> {

    Optional<Student> findByFullNameContainingIgnoreCase(String name);
    Page<Student> findAllByFullNameContainingIgnoreCaseAndFilial_NameContainingIgnoreCase(String fullName, String filialName, Pageable pageable);
}
