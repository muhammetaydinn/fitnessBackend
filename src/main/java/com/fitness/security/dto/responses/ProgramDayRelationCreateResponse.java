package com.fitness.security.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProgramDayRelationCreateResponse {

    private Integer id;
    private Integer exerciseDayRelationId;
    private Integer programId;
    private Integer userId;
    private String message;
    private boolean status;
}
