package com.library.librarymanagementspringboot.dto;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BookPatchDTO {

    @Size(max = 255)
    private String title;

    private Long authorId;

    @Size(max = 100)
    private String publisher;

    private LocalDate publishDate;

    @PositiveOrZero
    private BigDecimal price;

    @Size(max = 100)
    private String isbn;

    @PositiveOrZero
    private Integer quantity;

}
