package com.example.hucodeuz.repository;


import com.example.hucodeuz.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author "Husniddin Ulachov"
 * @created 2:50 PM on 6/26/2022
 * @project Edu-Center
 */
public interface StudentRepository extends JpaRepository<Student,Long> {
}
