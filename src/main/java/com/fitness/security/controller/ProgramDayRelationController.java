package com.fitness.security.controller;

import com.fitness.security.dto.request.ProgramDayRelationCreateRequest;
import com.fitness.security.dto.responses.ProgramDayRelationCreateResponse;
import com.fitness.security.service.ProgramDayRelationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/programDayRelation")
@CrossOrigin// backend and frontend communication
public class ProgramDayRelationController {

    private ProgramDayRelationService programDayRelationService;

    public ProgramDayRelationController(ProgramDayRelationService programDayRelationService) {
        this.programDayRelationService = programDayRelationService;
    }

    @PostMapping("/add")
    public ProgramDayRelationCreateResponse createOneProgramDayRelation(@RequestBody ProgramDayRelationCreateRequest programDayRelation) {
        return programDayRelationService.createOneProgramDayRelation(programDayRelation);
    }

}
