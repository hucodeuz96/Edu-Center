package com.example.hucodeuz.entity.templete;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author "Husniddin Ulachov"
 * @created 11:37 AM on 6/26/2022
 * @project Edu-Center
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@MappedSuperclass
public class AbsNameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
