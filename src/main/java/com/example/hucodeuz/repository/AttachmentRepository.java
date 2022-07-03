package com.example.hucodeuz.repository;

import com.example.hucodeuz.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author "Husniddin Ulachov"
 * @created 1:53 PM on 7/3/2022
 * @project Edu-Center
 */
public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}
