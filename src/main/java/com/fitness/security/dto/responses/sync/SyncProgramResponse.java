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
public class SyncProgramResponse {
    //list of programs
    //TODO: ALL PROGRAMID SHOULD BE NOT NULL
    List<SyncResProgramModel> programs;
}
