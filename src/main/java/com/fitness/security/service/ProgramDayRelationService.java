package com.fitness.security.service;

import com.fitness.security.dto.request.ProgramDayRelationCreateRequest;
import com.fitness.security.dto.responses.ProgramDayRelationCreateResponse;
import com.fitness.security.entity.ExerciseDayRelation;
import com.fitness.security.entity.Program;
import com.fitness.security.entity.ProgramDayRelation;
import com.fitness.security.entity.User;
import com.fitness.security.repository.ExerciseDayRelationRepository;
import com.fitness.security.repository.ProgramDayRelationRepository;
import com.fitness.security.repository.ProgramRepository;
import com.fitness.security.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service

public class ProgramDayRelationService {
    private ProgramDayRelationRepository programDayRelationRepository;
    private ProgramRepository programRepository;
    private UserRepository userRepository;
    private ExerciseDayRelationRepository exerciseDayRelationRepository;


    public ProgramDayRelationService(ProgramDayRelationRepository programDayRelationRepository, ProgramRepository programRepository, UserRepository userRepository, ExerciseDayRelationRepository exerciseDayRelationRepository) {
        this.programDayRelationRepository = programDayRelationRepository;
        this.programRepository = programRepository;
        this.userRepository = userRepository;
        this.exerciseDayRelationRepository = exerciseDayRelationRepository;
    }

    public ProgramDayRelationCreateResponse createOneProgramDayRelation(ProgramDayRelationCreateRequest request) {

        Program program = programRepository.findById(request.getProgramId()).orElse(null);
        User user = userRepository.findById(request.getUserId()).orElse(null);
        ExerciseDayRelation exerciseDayRelation = exerciseDayRelationRepository.findById(request.getExerciseDayRelationId()).orElse(null);

        ProgramDayRelation programDayRelation = new ProgramDayRelation();
        programDayRelation.setProgram(program);
        programDayRelation.setUser(user);
        programDayRelation.setExerciseDayRelation(exerciseDayRelation);
        ProgramDayRelation save = programDayRelationRepository.save(programDayRelation);


        ProgramDayRelationCreateResponse response = ProgramDayRelationCreateResponse.builder()
                .id(save.getId())
                .programId(programDayRelation.getProgram().getId())
                .userId(programDayRelation.getUser().getId())
                .exerciseDayRelationId(programDayRelation.getExerciseDayRelation().getId())
                .message("oluştuuuuuuuuu")
                .status(true)
                .build();


        return response;

    }
    // Gerekli servis metotları buraya gelecek
}