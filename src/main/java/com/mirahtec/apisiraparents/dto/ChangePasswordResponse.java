package com.mirahtec.apisiraparents.dto;

import lombok.Data;

@Data
public class ChangePasswordResponse {
    private boolean status;
    private String message;

    public ChangePasswordResponse() {
    }

    public ChangePasswordResponse(boolean success, String message) {
        this.status = success;
        this.message = message;
    }
}