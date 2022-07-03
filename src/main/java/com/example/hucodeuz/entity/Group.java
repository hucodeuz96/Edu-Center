package com.example.hucodeuz.entity;

/**
 * @author "Husniddin Ulachov"
 * @created 11:45 AM on 6/26/2022
 * @project Edu-Center
 */
import com.example.hucodeuz.entity.templete.AbsNameEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "groups")
@Builder
@Getter
@Setter
@ToString
public class Group extends AbsNameEntity {

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Course course;

    @ManyToOne
    private Filial filial;

}
