package com.library.librarymanagementspringboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BookRequestDTO {

    @NotBlank
    @Size(max = 255)
    private String title;

    @NotNull
    private Long authorId;

    @NotBlank
    @Size(max = 100)
    private String publisher;

    @NotNull
    private LocalDate publishDate;

    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    @NotBlank
    @Size(max = 100)
    private String isbn;

    @NotNull
    @PositiveOrZero
    private Integer quantity;

}
