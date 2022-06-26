package com.example.hucodeuz.entity;

import com.example.hucodeuz.entity.templete.AbsEntity;
import com.example.hucodeuz.entity.templete.AbsNameEntity;
import com.example.hucodeuz.enums.PositionType;

import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

/**
 * @author "Husniddin Ulachov"
 * @created 12:28 PM on 6/26/2022
 * @project Edu-Center
 */
import lombok.*;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@ToString
public class Staff  extends AbsEntity {
    private String fullName, phone;

    private Double salary;

    private boolean active = true;

    @ManyToOne
    private Filial filial;

    @Enumerated
    private PositionType positionType;
}
