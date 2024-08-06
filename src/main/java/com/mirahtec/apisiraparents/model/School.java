package com.mirahtec.apisiraparents.model;

import lombok.Data;

@Data
public class School {
    private Long id;
    private String name;
    private String address;
    private String telephone;
    private String email;
    private String website;
    private String logo;
    private String motto;
    public School() {
        name = "Les Coccinelles";
        address = "Rue 12 x 15";
        telephone = "0022899999999";
        email = "support@example.com";
        website = "www.coccinelles.com";
        logo = "logo.png";
        motto = "L'ecole des champions";
    }
}
