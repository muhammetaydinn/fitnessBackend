package com.fitness.security.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NewPassword {
    private String email;
    private String otp;
    private String newPassword;
}
