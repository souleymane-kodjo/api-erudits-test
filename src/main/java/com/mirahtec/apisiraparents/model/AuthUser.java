package com.mirahtec.apisiraparents.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.relational.core.mapping.Column;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("usersParents")
public class AuthUser {

    @Column( "id")
    @JsonIgnore
    private Long id;

    @Column("prenom")
    private String prenom ;
    @Column("nom")
    private String nom;
    @Column("telephone")
    private String telephone;
    @Column("email")
    private String email;
    @Column("password")
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String role;

    @Column("isActived")
    private Boolean isActived;
    @Column("isBlocked")
    @JsonIgnore
    private Boolean isBlocked ;
    @Column("isDeleted")
    @JsonIgnore
    private Boolean isDeleted ;

    public AuthUser() {
    }

}
