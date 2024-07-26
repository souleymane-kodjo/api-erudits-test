package com.mirahtec.apisiraparents.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Table(name = "journal_connexion")
@Data
public class JournalConnexion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private LocalDateTime dateConnexion;
    private String ipAddress;

    private String TypeDevice = "Web";

    public JournalConnexion() {
    }
    public JournalConnexion(String login, LocalDateTime dateConnexion, String ipAddress) {
        this.login = login;
        this.dateConnexion = dateConnexion;
        this.ipAddress = ipAddress;
    }
}