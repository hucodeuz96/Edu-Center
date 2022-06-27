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

public class GroupDto {
    @NotNull(message = "Nomini kirirtish shart")
    private String name;
    @NotNull(message = "Kursni kiritish kerak")
    private Long courseId;
    @NotNull(message = "Filialni kiritish kerak")
    private Long filialId;
}
