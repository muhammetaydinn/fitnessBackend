package com.fitness.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                //body responseEntity
                .body(
                        new RestErrorResponse(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                exception.getMessage(),
                                System.currentTimeMillis()
                        )
                );
    }

    @ExceptionHandler
    public ResponseEntity<RestErrorResponse> handleException(Exception e) {
        RestErrorResponse response = new RestErrorResponse();
        response.setStatus(
                HttpStatus.BAD_REQUEST.value()
        );// bad request
        response.setMessage(e.getMessage() + " - " + e.getClass().getSimpleName() + "- GlobalExceptionHandler");
        response.setTimeStamp(System.currentTimeMillis());
        return ResponseEntity.badRequest().body(response);
    }


}