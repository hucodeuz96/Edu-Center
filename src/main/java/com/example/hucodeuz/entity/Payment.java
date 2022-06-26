package com.example.hucodeuz.entity;

/**
 * @author "Husniddin Ulachov"
 * @created 11:52 AM on 6/26/2022
 * @project Edu-Center
 */
import com.example.hucodeuz.entity.templete.AbsEntity;
import com.example.hucodeuz.enums.PayType;
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
public class Payment extends AbsEntity {
    @ManyToOne
    private Student student;

    @ManyToOne
    private Filial filial;

    private Double amount;

    @Enumerated
    private PayType payType;



}
