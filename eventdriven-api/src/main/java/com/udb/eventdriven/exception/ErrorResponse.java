package com.udb.eventdriven.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {
    private LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String error;
    private String message;
    private List<String> detalles;

    public ErrorResponse(int status, String error, String message, List<String> detalles) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.detalles = detalles;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getError() { return error; }
    public String getMessage() { return message; }
    public List<String> getDetalles() { return detalles; }
}
