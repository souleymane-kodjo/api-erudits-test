package com.mirahtec.apisiraparents.controller.quittance;

import com.mirahtec.apisiraparents.model.Quittance;
import com.mirahtec.apisiraparents.service.documentService.QuittanceService;
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
@RequestMapping("/api/v1/quittances")
@Slf4j
public class QuittanceController {
    @Autowired
    private QuittanceService quittanceService;
    @GetMapping("/student/{matricule}")
    public ResponseEntity<?> getQuittancesByStudentMatricule(@PathVariable String matricule) {
        if (matricule == null || matricule.isEmpty()) {
            return ResponseEntity.badRequest().body("Matricule cannot be empty");
        }
        log.info("Get Quittances by student matricule: {}", matricule);
        List<Quittance> quittances = quittanceService.getQuittancesByStudentMatricule(matricule);
        ResponseEntity<?> response = new ResponseEntity<>(quittances, HttpStatus.OK);
        return response;
    }
}
