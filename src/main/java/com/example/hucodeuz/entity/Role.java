package com.example.hucodeuz.entity;

import com.example.hucodeuz.entity.templete.AbsNameEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author "Husniddin Ulachov"
 * @created 7:38 PM on 7/12/2022
 * @project Edu-Center
 */
@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean active;

}
