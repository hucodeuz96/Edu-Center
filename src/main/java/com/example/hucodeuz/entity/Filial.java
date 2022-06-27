package com.example.hucodeuz.entity;

/**
 * @author "Husniddin Ulachov"
 * @created 11:43 AM on 6/26/2022
 * @project Edu-Center
 */
import com.example.hucodeuz.entity.templete.AbsNameEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
public class Filial extends AbsNameEntity {
    private Boolean status = true;

    @JsonIgnore
    @OneToMany(mappedBy ="filial",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Group> groupList;

    @JsonIgnore
    @OneToMany(mappedBy = "filial",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Staff> staffList;

}
