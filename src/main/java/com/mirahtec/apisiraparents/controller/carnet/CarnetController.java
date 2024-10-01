package com.mirahtec.apisiraparents.controller.carnet;

import com.mirahtec.apisiraparents.model.Carnet;
import com.mirahtec.apisiraparents.service.carnetService.CarnetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carnets")
@Slf4j
public class CarnetController {
    @Autowired
    private CarnetService carnetService;

    @GetMapping("/student/{matricule}")
    public ResponseEntity<?> getCarnetsByStudentMatricule(@PathVariable String matricule) {
        if (matricule == null || matricule.isEmpty()) {
            return ResponseEntity.badRequest().body("Le matricule ne doit pas etre vide");
        }
        try {
            List<Carnet> carnets = carnetService.getCarnetsByStudentMatricule(matricule);
            if (carnets == null) {
                return ResponseEntity.notFound().build();
            }
            if (carnets.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(carnets);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("error logs de la recuperation du carnet");
        }
    }
}
