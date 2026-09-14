package com.library.librarymanagementspringboot.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberPatchDTO {

    @Size(max = 255)
    private String name;

    @Size(max = 20)
    private String phone;

    @Size(max = 255)
    private String address;

}
