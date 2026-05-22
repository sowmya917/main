package com.meka.oms.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String role;
}