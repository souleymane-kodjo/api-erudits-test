package com.mirahtec.apisiraparents.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Data
@Table("usersparents")
public class Parent implements Serializable  {
    @Id
    private Long id;

    @Column("prenom")
    private String prenom;

    @Column("nom")
    private String nom;

    @Column("telephone")
    private String telephone;

    @Column("email")
    private String email;

    @Column("password")
    private String password;
    @Column("role")
    private String role;

    public String getUsername() {
        return telephone;
    }
}