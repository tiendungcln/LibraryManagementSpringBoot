package com.library.librarymanagementspringboot.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AuthorPatchDTO {

    @Size(max = 255)
    private String name;

    @Size(max = 100)
    private String country;

    private LocalDate birthDate;

}