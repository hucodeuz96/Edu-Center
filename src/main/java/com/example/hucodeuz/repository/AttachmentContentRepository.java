package com.example.hucodeuz.repository;

import com.example.hucodeuz.entity.Attachment;
import com.example.hucodeuz.entity.AttachmentContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author "Husniddin Ulachov"
 * @created 6:57 PM on 7/3/2022
 * @project Edu-Center
 */
public interface AttachmentContentRepository extends JpaRepository <AttachmentContent, UUID>{
        AttachmentContent findByAttachment(Attachment attachment);

}
