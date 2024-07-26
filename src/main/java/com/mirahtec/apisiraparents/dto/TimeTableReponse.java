// src/main/java/com/mirahtec/apisiraparents/dto/DaySchedule.java
package com.mirahtec.apisiraparents.dto;

import com.mirahtec.apisiraparents.model.Subject;
import lombok.Data;


import java.io.Serializable;
import java.util.List;
@Data
public class TimeTableReponse implements Serializable {
    private String id;
    private String jour;
    private int dayDate;
    private List<Subject> matieres;

    public TimeTableReponse() {
        this.id = "1";
        this.jour = "Lun";
        this.dayDate = 15;
        this.matieres = List.of(
                new Subject(1L, "08:00 - 09:00", "Mathématiques", "math", "M. Jean", "#FF0000", "#FFFFFF"),
                new Subject(2L, "09:00 - 10:00", "Français", "fr", "M. Paul", "#00FF00", "#FFFFFF")
        );
    }
    public List<Subject> getMatieres() {
        return matieres;
    }

    public void setMatieres(List<Subject> matieres) {
        this.matieres = matieres;
    }
}