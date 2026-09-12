package com.library.librarymanagementspringboot.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AuthorPatchDTO {

    private String name;
    private String country;
    private LocalDate birthDate;

}