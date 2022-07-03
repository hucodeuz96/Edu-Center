package com.example.hucodeuz.controller;

import com.example.hucodeuz.dto.ApiResponse;
import com.example.hucodeuz.entity.Attachment;
import com.example.hucodeuz.exception.ResourceNotFoundException;
import com.example.hucodeuz.repository.AttachmentRepository;
import com.example.hucodeuz.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


/**
 * @author "Husniddin Ulachov"
 * @created 1:52 PM on 7/3/2022
 * @project Edu-Center
 */
@RequestMapping("/attachment")
@RequiredArgsConstructor
@RestController
public class AttachmentController {
    private final AttachmentService attachmentService;
    private final AttachmentRepository attachmentRepository;
    private final Path root = Paths.get("src/main/resources/upload");
    @PostMapping
    public ResponseEntity<?> upload(MultipartHttpServletRequest request){
        ApiResponse<?> apiResponse = attachmentService.uploadFileSystem(request);
        return ResponseEntity.ok(apiResponse);
    }
    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<?> getFile(@PathVariable UUID id){
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("file", "id", id));
        Path file = root.resolve(attachment.getFileName());

        Resource resource = new UrlResource(file.toUri());
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(attachment.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+attachment.getFileName()+"\"")
                .body(resource);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable UUID id){
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("file", "id", id));
        Path file = root.resolve(attachment.getFileName());
        boolean b = FileSystemUtils.deleteRecursively(file.toFile());
        if (b){
            attachmentRepository.deleteById(id);
        }
        return ResponseEntity.ok(id +" - nomli file uchirildi");
    }
    @PostMapping("/uploadDB")
    public ResponseEntity<?> saveToDb(MultipartHttpServletRequest request){
        ApiResponse<?> response = attachmentService.uploadDB(request);
        return ResponseEntity.status(response.isSuccess()?201:409).body(response);
    }
    @GetMapping("/downloadDB/{attachmentId}")
    public ResponseEntity<?> getFromDB(@PathVariable(value = "attachmentId") UUID id){
        return  attachmentService.downloadDb(id);
    }
    @DeleteMapping("/deleteDB/{id}")
    public ResponseEntity<?> remove(@PathVariable UUID id){
      return   attachmentService.deleteDB(id);
    }
}
