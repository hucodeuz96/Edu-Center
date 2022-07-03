package com.example.hucodeuz.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @author "Husniddin Ulachov"
 * @created 2:28 PM on 6/26/2022
 * @project Edu-Center
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResPaymentDto {
    private String student;
    private String filial;
    private Double amount;
    private String payType;
    private String createdAt;
    private String updatedAt;
}
