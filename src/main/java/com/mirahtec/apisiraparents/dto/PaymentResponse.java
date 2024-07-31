package com.mirahtec.apisiraparents.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.mirahtec.apisiraparents.model.Payment;
import lombok.Data;

import java.util.List;
@Data
public class PaymentResponse {
    @JsonAlias("cumule_paiements")
    private double cumule_paiements ;
    @JsonAlias("impayes")
    private double Impayes;
    @JsonAlias("paiements")
    List<Payment> payments ;
}
