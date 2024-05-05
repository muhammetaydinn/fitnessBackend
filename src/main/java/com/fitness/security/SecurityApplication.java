package com.fitness.security;

import com.fitness.security.service.AuthenticationService;
import com.fitness.security.service.EmailSenderService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SecurityApplication {


    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);

    }

  

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service) {
        return args -> {
            // dummy data

            // var admin = RegisterRequest.builder()
            // .firstname("Admin")
            // .lastname("Admin")
            // .email("admin@mail.com")
            // .password("password")
            // .role(ADMIN)
            // .build();
            // System.out.println("Admin token: " +
            // service.register(admin).getAccessToken());
            //
            // var manager = RegisterRequest.builder()
            // .firstname("Admin")
            // .lastname("Admin")
            // .email("manager@mail.com")
            // .password("password")
            // .role(MANAGER)
            // .build();
            // System.out.println("Manager token: " +
            // service.register(manager).getAccessToken());

        };
    }
}
