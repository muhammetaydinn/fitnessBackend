package com.fitness.security.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ExerciseCreateRequest {
    private Integer setCount;
    private Integer reps;
    private Integer weightDuration;
    private Integer movementId;
}
