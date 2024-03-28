package com.fitness.security.dto.responses.sync;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SyncResExerciseModel {
    private Integer setCount;
    private Integer reps;
    private Integer weightDuration;
    private Integer movementId;
}
