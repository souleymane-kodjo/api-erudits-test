package com.mirahtec.apisiraparents.dto;

import lombok.Data;

@Data
public class DayRequest {
    private String day;
    private String classId;

    public String getDay() {
        return day;
    }
}