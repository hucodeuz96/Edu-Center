package com.example.hucodeuz.repository;


import com.example.hucodeuz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author "Husniddin Ulachov"
 * @created 2:50 PM on 6/26/2022
 * @project Edu-Center
 */
@RepositoryRestResource(path = "user")
public interface UserRepository extends JpaRepository<User, UUID> {
    @RestResource(path = "phone")
    List<User> findByPhoneStartingWith(@Param("phone") String phone);

    @RestResource(path = "fullName")
    List<User> findByFullNameStartingWith(@Param("fullName") String fullName);
    Optional<UserDetails> findByPhone(String phone);

}
