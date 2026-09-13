package com.library.librarymanagementspringboot.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowRequestDTO {

    @NotNull
    private Long bookId;

    @NotNull
    private Long memberId;

}
