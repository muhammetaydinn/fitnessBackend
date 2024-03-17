//package com.fitness.security.controller;
//
//import com.fitness.security.dto.ProgramRequestDTO;
//import com.fitness.security.service.ProgramService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/v1/programs")
//public class ProgramController {
//    private final ProgramService programService;
//
//    @Autowired
//    public ProgramController(ProgramService programService) {
//        this.programService = programService;
//    }
//
//    @PostMapping("/addProgram")
//    public void addProgram(@RequestBody ProgramRequestDTO programDTO) {
//        programService.addProgram(programDTO);
//    }
//}
