package com.example.hucodeuz.entity;

/**
 * @author "Husniddin Ulachov"
 * @created 11:55 AM on 6/26/2022
 * @project Edu-Center
 */

import com.example.hucodeuz.entity.templete.AbsEntity;
import com.example.hucodeuz.enums.PositionType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@ToString
public class Teacher extends AbsEntity {

    private String fullName,phone;

    @ManyToOne
    private Course course;

    private Double balance;

    private boolean active;

    @Enumerated
    private PositionType positionType = PositionType.MENTOR;



}
