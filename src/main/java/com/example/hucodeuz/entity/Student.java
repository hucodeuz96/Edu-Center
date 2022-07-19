package com.example.hucodeuz.entity;

/**
 * @author "Husniddin Ulachov"
 * @created 11:48 AM on 6/26/2022
 * @project Edu-Center
 */
import com.example.hucodeuz.entity.templete.AbsEntity;
import com.example.hucodeuz.entity.templete.AbsNameEntity;
import com.example.hucodeuz.enums.GenderType;
import com.example.hucodeuz.enums.StudentStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@ToString
public class Student extends AbsEntity {
    @ManyToOne
    private Filial filial;

    @Column(nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    private GenderType genderType;

    private Double balance;

    private Date birth;

    private String phone;

    private String address;

    private String cardNumber;

    private String placeOfIssue;

    private String contractNumber;

    private Date contractDate;

    @ManyToMany
    private List<Course> course;

    @ManyToMany
    private List<Group> groups;

    @Enumerated(EnumType.STRING)
    private StudentStatus status;


}
