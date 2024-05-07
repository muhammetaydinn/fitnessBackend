package com.fitness.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
        //bad credentials
        if (exception.getMessage().contains("Bad credentials")) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(
                            new RestErrorResponse(
                                    HttpStatus.UNAUTHORIZED.value(),
                                    exception.getMessage() + " - One or more fields are empty or invalid",
                                    System.currentTimeMillis()));
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                // body responseEntity
                .body(
                        new RestErrorResponse(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                exception.getMessage() + " - RuntimeException",
                                System.currentTimeMillis()));
    }

    @ExceptionHandler({IllegalStateException.class})
    public ResponseEntity<Object> handleIllegalStateExceptions(RuntimeException exception) {
        //if exception contains Bad credentials at authenticate method
        if (exception.getMessage().contains("Bad credentials at authenticate method")) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(
                            new RestErrorResponse(
                                    HttpStatus.UNAUTHORIZED.value(),
                                    "Login Failed One or more fields or invalid",
                                    System.currentTimeMillis()));
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new RestErrorResponse(
                                HttpStatus.BAD_REQUEST.value(),
                                exception.getMessage(),
                                System.currentTimeMillis()));
    }

    @ExceptionHandler
    public ResponseEntity<RestErrorResponse> handleException(Exception e) {
        RestErrorResponse response = new RestErrorResponse();
        response.setStatus(
                HttpStatus.BAD_REQUEST.value());// bad request
        response.setMessage(e.getMessage() + " - " + e.getClass().getSimpleName() + "- GlobalExceptionHandler");
        response.setTimeStamp(System.currentTimeMillis());
        return ResponseEntity.badRequest().body(response);
    }

}