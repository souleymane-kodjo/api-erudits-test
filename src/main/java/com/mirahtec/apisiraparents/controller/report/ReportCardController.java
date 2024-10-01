package com.mirahtec.apisiraparents.controller.report;

import com.mirahtec.apisiraparents.model.ReportCard;
import com.mirahtec.apisiraparents.service.reportCardService.ReportCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/report-cards")
@Slf4j
public class ReportCardController {
    @Autowired
    private ReportCardService reportCardService;
    //Get report cards by student matricule
    @GetMapping("/student/{matricule}")
    public ResponseEntity<?> getReportCardsByStudentMatricule(@PathVariable String matricule) {
        if (matricule == null || matricule.isEmpty()) {
            return ResponseEntity.badRequest().body("Le matricule ne doit pas etre vide");
        }
      List<ReportCard> reportCards = reportCardService.getReportCardsByStudentMatricule(matricule);
        HashMap<String, String> response = new HashMap<>();
        if (reportCards == null || reportCards.isEmpty()) {
            response.put("message", "Audu bulletin trouvé pour ce matricule");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(reportCards);
    }
}