package com.fitness.security.controller;

import com.fitness.security.service.UserService;
import com.fitness.security.user.ChangePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PatchMapping
    public ResponseEntity changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser) {

        return service.changePassword(request, connectedUser);
    }

    @PostMapping("/reset-password-otp")
    public ResponseEntity resetPasswordSendOtp(@RequestBody ResetPasswordRequest request) {
        return service.resetPasswordSendOtp(request);
    }

}
