package com.example.hucodeuz.service;

import com.example.hucodeuz.dto.ApiResponse;
import com.example.hucodeuz.entity.Attachment;
import com.example.hucodeuz.entity.AttachmentContent;
import com.example.hucodeuz.exception.ResourceNotFoundException;
import com.example.hucodeuz.repository.AttachmentContentRepository;
import com.example.hucodeuz.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.UUID;

/**
 * @author "Husniddin Ulachov"
 * @created 1:55 PM on 7/3/2022
 * @project Edu-Center
 */
@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository contentRepository;
    private final Path root = Paths.get("src/main/resources/upload");
    @SneakyThrows
    public ApiResponse<?> uploadFileSystem(MultipartHttpServletRequest request) {
//       MultipartFile file = request.getFile("file");
        Iterator<String> fileNames = request.getFileNames();
        Attachment save = null;
        while (fileNames.hasNext()) {
            MultipartFile file = request.getFile(fileNames.next());
            Attachment attachment = new Attachment();
            attachment.setFileName(file.getOriginalFilename());
            attachment.setSize(file.getSize());
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
            attachment.setContentType(file.getContentType());
            attachment.setUrl(this.root + file.getOriginalFilename());
            save = attachmentRepository.save(attachment);
        }
        return ApiResponse.builder()
                .success(true)
                .message(save.getFileName()+ "  nomli fayli saqlandi")
                .build();
    }

    @SneakyThrows
    public ApiResponse<?> uploadDB(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        while (fileNames.hasNext()) {
            MultipartFile file = request.getFile(fileNames.next());
            if (!file.isEmpty() || file != null) {
                Attachment attachment = new Attachment();
                attachment.setSize(file.getSize());
                attachment.setFileName(file.getName());
                attachment.setContentType(file.getContentType());
                Attachment attachment1 = attachmentRepository.save(attachment);

                contentRepository.save(AttachmentContent.builder()
                        .attachment(attachment1)
                        .bytes(file.getBytes())
                        .build());
            }
        }
        return ApiResponse.builder().message("chotki").success(true).build();
    }

    public ResponseEntity<?> downloadDb(UUID id) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("file", "id ", id));
        AttachmentContent byAttachment = contentRepository.findByAttachment(attachment);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(attachment.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + attachment.getFileName() + "\"")
                .body(byAttachment);
    }
    public ResponseEntity<?> deleteDB(UUID id) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Attachment", " id ", id));
        AttachmentContent byAttachment = contentRepository.findByAttachment(attachment);
        contentRepository.delete(byAttachment);
        attachmentRepository.deleteById(id);
        return ResponseEntity.ok("Attachment deleted");
    }
}
