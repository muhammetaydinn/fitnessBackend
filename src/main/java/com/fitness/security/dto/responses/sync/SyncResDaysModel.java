package com.fitness.security.dto.responses.sync;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SyncResDaysModel {
    private String name;
    private List<SyncResExerciseModel> exercises;
}
