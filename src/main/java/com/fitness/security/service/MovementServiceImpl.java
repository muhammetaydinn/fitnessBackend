package com.fitness.security.service;

import com.fitness.security.dto.responses.GetMovementByIdResponse;
import com.fitness.security.repository.MovementRepository;
import com.fitness.security.util.mappers.ModelMapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovementServiceImpl implements MovementService {
    private MovementRepository movementRepository;
    private ModelMapperService modelMapperService;

    @Override
    public GetMovementByIdResponse getMovementById(int id) {
        return this.modelMapperService.forResponse().map(movementRepository.findById(id), GetMovementByIdResponse.class);
    }
}
