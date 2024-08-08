package com.orange.eduback.dto;

import lombok.Data;

@Data
public class UserInfoDto {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String address;
    private String role;
}
