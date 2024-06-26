package com.fitness.security.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProgramCreateRequest {
    // id can be null
    private Optional<Integer> id;
    private String name;
    private List<DayCreateRequest> days;

}
