package com.library.librarymanagementspringboot.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseDTO {

    private int status;
    private String message;
    private LocalDateTime timestamp;
    private Map<String, String> errors;

}
