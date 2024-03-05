package com.fitness.security.exception;

import lombok.Getter;

@Getter
public class RestErrorResponse {
    private int status;
    private String message;
    private long timeStamp;

    public RestErrorResponse() {
    }

    public RestErrorResponse(int status, String message, long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
