package com.example.hucodeuz.entity;

import com.example.hucodeuz.entity.templete.AbsEntity;
import lombok.*;

import javax.persistence.Entity;

/**
 * @author "Husniddin Ulachov"
 * @created 1:48 PM on 7/3/2022
 * @project Edu-Center
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Attachment extends AbsEntity {
    private String fileName;
    private String contentType;
    private long size;
    private String url;

}
