package com.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class ErrorDTO {

    private int status;
    private String message;
    private Date timestamp;

    public ErrorDTO setStatus(int status) {
        this.status = status;
        return this;
    }

    public ErrorDTO setMessage(String message) {
        this.message = message;
        return this;
    }

    public ErrorDTO setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
