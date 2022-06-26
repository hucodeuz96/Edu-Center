package com.example.hucodeuz.entity;

/**
 * @author "Husniddin Ulachov"
 * @created 12:10 PM on 6/26/2022
 * @project Edu-Center
 */
import com.example.hucodeuz.entity.templete.AbsEntity;
import com.example.hucodeuz.entity.templete.AbsNameEntity;
import com.example.hucodeuz.enums.ExpansePayType;
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
public class Expanse extends AbsNameEntity {
    private String description;

    @ManyToOne
    private Filial filial;

    @Enumerated
    private ExpansePayType expansePayType;

    private Double amount;

    @Enumerated
    private PayType payType;
}
