package com.fitness.security.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProgramDayRelationCreateRequest {

    private Integer exerciseDayRelationId;
    private Integer programId;
    private Integer userId;
}
