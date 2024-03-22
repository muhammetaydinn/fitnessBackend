package com.fitness.security.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DayCreateRequest {
    private String name;
    private List<ExerciseCreateRequest> exercises;
}
