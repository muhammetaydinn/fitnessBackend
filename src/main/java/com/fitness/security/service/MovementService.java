package com.fitness.security.service;

import com.fitness.security.dto.responses.GetMovementByIdResponse;

public interface MovementService {
    //get movement by id
    GetMovementByIdResponse getMovementById(int id);
    // bunu test amaclı yazdım. Onun dısındaki her sey aslında ProgramService deki logiclere göre olacak ona göre buuraya da eklenecek.
    

}