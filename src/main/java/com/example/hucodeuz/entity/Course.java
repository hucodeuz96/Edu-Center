package com.example.hucodeuz.entity;

/**
 * @author "Husniddin Ulachov"
 * @created 11:40 AM on 6/26/2022
 * @project Edu-Center
 */
import com.example.hucodeuz.entity.templete.AbsNameEntity;
import lombok.*;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@ToString
public class Course extends AbsNameEntity {
    private Double price;

}
