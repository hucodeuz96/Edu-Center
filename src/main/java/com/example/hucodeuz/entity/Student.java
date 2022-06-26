package com.example.hucodeuz.entity;

/**
 * @author "Husniddin Ulachov"
 * @created 11:48 AM on 6/26/2022
 * @project Edu-Center
 */
import com.example.hucodeuz.entity.templete.AbsEntity;
import com.example.hucodeuz.entity.templete.AbsNameEntity;
import com.example.hucodeuz.enums.StudentStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@ToString
public class Student extends AbsEntity {

    private String fullName,phone;
    private Double balance;

    @ManyToMany
    private List<Group> groups;

    @Enumerated(EnumType.STRING)
    private StudentStatus status;


}
