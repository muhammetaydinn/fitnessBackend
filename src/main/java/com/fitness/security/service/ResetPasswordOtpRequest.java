package com.fitness.security.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResetPasswordOtpRequest {
    private String email;
    private String otp;
}
