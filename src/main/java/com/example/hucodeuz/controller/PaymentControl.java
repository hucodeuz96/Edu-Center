package com.example.hucodeuz.controller;

import com.example.hucodeuz.dto.ApiResponse;
import com.example.hucodeuz.dto.PaymentDto;
import com.example.hucodeuz.entity.Payment;
import com.example.hucodeuz.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentControl {
    private final PaymentService paymentService;
    @PreAuthorize(value = "hasAuthority('CREATE')")
    @PostMapping
    public ResponseEntity<?> createPayment(@Valid @RequestBody PaymentDto paymentDto) {
        ApiResponse<?> apiResponse = paymentService.save(paymentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam (defaultValue = "0")  int page,
            @RequestParam (defaultValue = "10") int size,
            @RequestParam (defaultValue = "")  String filial,
            @RequestParam (defaultValue = "") String student,
            @RequestParam (defaultValue = "") String startDate,
            @RequestParam (defaultValue = "") String endDate
    ) {
        ApiResponse<?> apiResponse = paymentService.getAll(page,size,filial,student,startDate,endDate);
        return ResponseEntity.ok(apiResponse);
    }
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable String id) {
        ApiResponse<?> one = paymentService.getOne(id);
        return new ResponseEntity<>(one, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        ApiResponse<String> delete = paymentService.delete(id);
        return ResponseEntity.ok(delete);
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
