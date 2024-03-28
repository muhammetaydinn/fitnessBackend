package com.fitness.security.controller;

import com.fitness.security.dto.request.ProgramCreateRequest;
import com.fitness.security.dto.request.SyncProgramRequest;
import com.fitness.security.dto.responses.CreateProgramResponse;
import com.fitness.security.dto.responses.ProgramDeleteResponse;
import com.fitness.security.dto.responses.sync.SyncProgramResponse;
import com.fitness.security.service.ProgramService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/program")
@CrossOrigin// backend and frontend communication
public class ProgramController {

    private ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @PostMapping()
    public CreateProgramResponse createOneProgram(@RequestBody ProgramCreateRequest programCreateRequest,
                                                  @RequestHeader("Authorization") String accessToken) {
        //trim the access token
        accessToken = accessToken.substring(7);
        return programService.createOneProgram(programCreateRequest, accessToken);
    }

    @DeleteMapping("/{id}")
    public ProgramDeleteResponse deleteProgram(@PathVariable int id) {
        return programService.deleteProgram(id);
    }

    @PostMapping("/sync")
    public SyncProgramResponse syncProgram(@RequestBody SyncProgramRequest syncProgramRequest,
                                           @RequestHeader("Authorization") String accessToken) {
        //trim the access token
        accessToken = accessToken.substring(7);
        return programService.syncPrograms(syncProgramRequest, accessToken);
    }


}