package com.library.librarymanagementspringboot.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BookPatchDTO {

    private String title;
    private Long authorId;
    private String publisher;
    private LocalDate publishDate;
    private BigDecimal price;
    private String isbn;
    private Integer quantity;

}
