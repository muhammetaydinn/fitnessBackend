package com.fitness.security.service;

import com.fitness.security.dto.request.ProgramCreateRequest;
import com.fitness.security.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgramService {
    // private MovementRepository movementRepository;
    // private ExerciseRepository exerciseRepository;
    // private DayRepository dayRepository;
    // private ExerciseDayRelationRepository exerciseDayRelationRepository;
    // private UserRepository userRepository;
    // private ProgramRepository programRepository;
    // private ProgramDayRelationRepository programDayRelationRepository;


    public void createOneProgram(ProgramCreateRequest programCreateRequest) {
        System.out.println("ProgramService.createOneProgram");


    }


//    public void addProgram(ProgramRequestDTO programDTO) {
//        // Programı kaydet
//
//    }
}