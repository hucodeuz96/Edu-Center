package com.example.hucodeuz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResGroupDTO {
    private String name;
    private String filialName;
    private String courseName;
}
