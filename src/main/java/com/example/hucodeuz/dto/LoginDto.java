package com.example.hucodeuz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author "Husniddin Ulachov"
 * @created 4:03 PM on 7/16/2022
 * @project Edu-Center
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDto {
    private String username;
    private String password;
}
