package com.fitness.security.controller;

import com.fitness.security.dto.request.ProgramCreateRequest;
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

    @PostMapping("/add")
    public void createOneProgram(@RequestBody ProgramCreateRequest programCreateRequest) {
        System.out.println(programCreateRequest);
        programService.createOneProgram(programCreateRequest);
    }
}