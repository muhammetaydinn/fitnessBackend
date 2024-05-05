package com.fitness.security.service;

import com.fitness.security.controller.ResetPasswordRequest;
import com.fitness.security.entity.Otp;
import com.fitness.security.entity.User;
import com.fitness.security.repository.OtpRepository;
import com.fitness.security.repository.UserRepository;
import com.fitness.security.user.ChangePasswordRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private EmailSenderService emailSenderService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final OtpRepository otpRepository;

    public ResponseEntity changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        repository.save(user);
        return ResponseEntity.ok("Password changed successfully");
    }

    public ResponseEntity resetPasswordSendOtp(ResetPasswordRequest request) {
        // check if the email exists
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalStateException("Email not found"));

        if (user != null) {
            String otp = generateOTP();
            // expiration is 5 minutes
            long expiration_date = System.currentTimeMillis() + 300000;
            otpRepository.save(new Otp(null, otp, user, false, expiration_date));
            sendEmail(user.getEmail(), otp);
            // Todo: when otp used change is_used to true
        } else {
            // Kullanıcı bulunamadı hatası
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok("OTP sent successfully");
    }

    private String generateOTP() {
        Random random = new Random();
        int otpLength = 6;
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < otpLength; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }

    private void sendEmail(String email, String otp) {
        emailSenderService.sendEmail(email, otp);
    }
}
