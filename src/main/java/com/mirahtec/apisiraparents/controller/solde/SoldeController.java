package com.mirahtec.apisiraparents.controller;

import com.mirahtec.apisiraparents.model.Payment;
import com.mirahtec.apisiraparents.service.SoldeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/soldes")
@Slf4j
public class SoldeController {
    @Autowired
    private SoldeService soldeService;
    @GetMapping("/student/{matricule}")
    public ResponseEntity<?> getSoldesByStudentMatricule(@PathVariable String matricule) {

        if (matricule == null || matricule.isEmpty()) {
            return ResponseEntity.badRequest().body("Matricule cannot be empty");
        }
        log.info("Get Soldes by student matricule: {}", matricule);
        List<Payment> soldes = soldeService.getSoldesByStudentMatricule(matricule);
        ResponseEntity<?> response = new ResponseEntity<>(soldes, HttpStatus.OK);
        return response;
    }
}
