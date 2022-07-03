package com.example.hucodeuz.repository;

import com.example.hucodeuz.entity.Filial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;


import java.util.List;
import java.util.Optional;

/**
 * @author "Husniddin Ulachov"
 * @created 2:50 PM on 6/26/2022
 * @project Edu-Center
 */
@RepositoryRestResource(path = "filial")
public interface FilialRepository extends JpaRepository<Filial,Long> {
    @RestResource(path = "name")
    Optional<Filial>  findByNameContainingIgnoreCase(@Param("name") String name);

}
