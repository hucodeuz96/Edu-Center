package com.example.hucodeuz.controller;

import com.example.hucodeuz.dto.ApiResponse;
import com.example.hucodeuz.dto.GroupDto;
import com.example.hucodeuz.entity.Group;
import com.example.hucodeuz.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author "Husniddin Ulachov"
 * @created 2:39 PM on 6/26/2022
 * @project Edu-Center
 */
@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupControl {
    private  final GroupService groupService;

    @PostMapping
    public ResponseEntity<?> createGroup(@Valid @RequestBody GroupDto groupDto){
        ApiResponse<Group> apiResponse = groupService.save(groupDto);
        return ResponseEntity.status(apiResponse.isSuccess()? 201 : 409).body(apiResponse);
    }
    @GetMapping
    public ResponseEntity<?> getAll(){
        return null;
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@RequestParam Long id){
        return null;
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestParam Long id, @RequestBody GroupDto groupDto){
        return null;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestParam Long id){
        return null;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
