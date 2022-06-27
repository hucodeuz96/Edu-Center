package com.example.hucodeuz.entity.templete;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * @author "Husniddin Ulachov"
 * @created 10:52 AM on 6/26/2022
 * @project Edu-Center
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@MappedSuperclass
public class AbsEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
//
//    @LastModifiedBy
//    private UUID updatedBy;

//    @CreationTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(updatable = false)
//    private Date createdAt;

//    @CreationTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(nullable = false)
//    private Date updatedAt;


}
