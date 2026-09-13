package com.library.librarymanagementspringboot.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BorrowPatchDTO {

    private Long bookId;
    private Long memberId;
    private LocalDateTime borrowedAt;
    private LocalDateTime returnedAt;

}
