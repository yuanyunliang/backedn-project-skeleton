package com.orange.eduback.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String token;
    private String username;
    private String role;
}
