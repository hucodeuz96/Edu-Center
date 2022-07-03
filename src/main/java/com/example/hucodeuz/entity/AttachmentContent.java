package com.example.hucodeuz.entity;

import com.example.hucodeuz.entity.templete.AbsEntity;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @author "Husniddin Ulachov"
 * @created 6:53 PM on 7/3/2022
 * @project Edu-Center
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class AttachmentContent extends AbsEntity {
    @OneToOne
    private Attachment attachment;
    private byte[] bytes;


}
