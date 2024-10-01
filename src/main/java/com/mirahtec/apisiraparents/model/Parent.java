package com.mirahtec.apisiraparents.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column("prenomParent")
    private String prenomParent;

    @Column("nomParent")
    private String nomParent;

    @Column("telephone")
    private String telephone;

    @Column("email")
    private String email;

    @Column("password")
    @JsonIgnore
    private String password;
    @Column("role")
    private String role;

    //

    public String getUsername() {
        return telephone;
    }

    public String getRole() {
        return "PARENT_ELEVE";
    }

}