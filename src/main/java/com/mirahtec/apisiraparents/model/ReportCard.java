package com.mirahtec.apisiraparents.model;

import com.mirahtec.apisiraparents.utils.ParserString;
import lombok.Data;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
@Data
public class ReportCard implements Serializable {
    private Long id;
    private String matricule;
    private String type;
    private String date ;
    private String pj;
    private String publie;
    private String nom ;
    private String link ;
    public String getLink() {
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return baseUrl + "/Documents/Etudiants/"+matricule+ "/" + ParserString.UriParser.parseUri(pj);
    }
}
