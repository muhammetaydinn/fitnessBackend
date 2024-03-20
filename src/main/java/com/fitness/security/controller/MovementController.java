package com.fitness.security.controller;

import com.fitness.security.dto.responses.GetMovementByIdResponse;
import com.fitness.security.service.MovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class MovementController {

    private final MovementService movementService;

    @GetMapping("/movements/{id}")
    public ResponseEntity<GetMovementByIdResponse> getMovementById(@PathVariable int id) {
        GetMovementByIdResponse getMovementByIdResponse = movementService.getMovementById(id);
        return ResponseEntity.ok(getMovementByIdResponse);
        // MovementService'ten Movement'ı al
//        Movement movement = movementService.getMovementById(id)
//                .orElseThrow(() -> new RuntimeException("Movement not found with id: " + id));
//        GetMovementByIdResponse response = new GetMovementByIdResponse();
//
//        try {
//            response.setId(movement.getId());
//            response.setName(movement.getName());
//            response.setForceType(movement.getForceType());
//            response.setLevel(movement.getLevel());
//            response.setMechanic(movement.getMechanic());
//            response.setEquipment(movement.getEquipment());
//            response.setPrimaryMuscles(movement.getPrimaryMusclesList());
//            response.setSecondaryMuscles(movement.getSecondaryMusclesList());
//            response.setInstructions(movement.getInstructionsList());
//            response.setCategory(movement.getCategory());
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

    }
}