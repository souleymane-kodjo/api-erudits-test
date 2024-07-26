package com.mirahtec.apisiraparents.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class PresenceResponse {@Id
private Long id;


    private String matricule;

    private String classe;

    private String cours ;

    private LocalDate date;

    private String presence;

    private LocalDateTime dateSaisie;
}
