package com.orange.eduback.dto;

import lombok.Data;

@Data
public class EmailDto {
    private String email;
    public EmailDto(String email) {
        this.email = email;
    }
}
