package com.library.librarymanagementspringboot.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BorrowResponseDTO {

    private Long borrowId;
    private Long bookId;
    private Long memberId;
    private LocalDateTime borrowedAt;
    private LocalDateTime returnedAt;

}
