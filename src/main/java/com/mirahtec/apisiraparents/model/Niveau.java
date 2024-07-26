package com.mirahtec.apisiraparents.model;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("niveaux")
public class Niveau {
    @Column
    private Long idNiveau;
    @Column
    private String nomNiveau;
}
