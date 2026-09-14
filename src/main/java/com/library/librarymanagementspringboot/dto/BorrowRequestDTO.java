package com.library.librarymanagementspringboot.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowRequestDTO {

    @NotNull
    @Positive
    private Long bookId;

    @NotNull
    @Positive
    private Long memberId;

}
