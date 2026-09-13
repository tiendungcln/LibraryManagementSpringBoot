package com.library.librarymanagementspringboot.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemberResponseDTO {

    private Long memberId;
    private String name;
    private String phone;
    private String address;
    private LocalDate registeredDate;

}
