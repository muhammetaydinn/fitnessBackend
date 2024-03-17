package com.fitness.security.service;

import com.fitness.security.repository.MovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovementService {
    private final MovementRepository movementRepository;

    // Gerekli servis metotları buraya gelecek
}