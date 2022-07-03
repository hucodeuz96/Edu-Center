package com.example.hucodeuz.entity;

/**
 * @author "Husniddin Ulachov"
 * @created 11:52 AM on 6/26/2022
 * @project Edu-Center
 */

import com.example.hucodeuz.entity.templete.AbsEntity;
import com.example.hucodeuz.enums.PayType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@ToString
public class Payment extends AbsEntity {

    @ManyToOne
    private Student student;

    @ManyToOne
    private Filial filial;

    private Double amount;

    @Enumerated(value = EnumType.STRING)
    private PayType payType;




}
