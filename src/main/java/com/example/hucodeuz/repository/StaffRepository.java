package com.example.hucodeuz.repository;


import com.example.hucodeuz.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;


import java.util.List;
import java.util.UUID;

/**
 * @author "Husniddin Ulachov"
 * @created 2:50 PM on 6/26/2022
 * @project Edu-Center
 */
@RepositoryRestResource(path = "staff")
public interface StaffRepository extends JpaRepository<Staff, UUID> {
    @RestResource(path = "phone")
    List<Staff> findByPhoneStartingWith(@Param("phone") String phone);

    @RestResource(path = "fullName")
    List<Staff> findByFullNameStartingWith(@Param("fullName") String fullName);

}
