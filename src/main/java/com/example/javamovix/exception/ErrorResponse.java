package com.example.javamovix.exception;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public class ErrorResponse {
    public final String message;
    public final HttpStatus status;
    public final LocalDateTime time;

    public ErrorResponse(String message, HttpStatus status, LocalDateTime time) {
        this.message = message;
        this.status = status;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getTime() {
        return time;
    }
}

