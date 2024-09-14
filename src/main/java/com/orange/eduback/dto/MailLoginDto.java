package com.orange.eduback.dto;

import lombok.Data;

@Data
public class MailLoginDto {
    private String mail;
    private String verifyCode;
}
