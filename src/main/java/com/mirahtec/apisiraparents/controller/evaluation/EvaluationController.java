package com.mirahtec.apisiraparents.controller.evaluation;

import com.mirahtec.apisiraparents.service.evaluationService.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/evaluations")
public class EvaluationController {
    @Autowired
    private EvaluationService noteService;

    @GetMapping("/student/class/{classID}")
    public ResponseEntity<?> getevaluationsByStudentMatricule(@PathVariable String classID) {
        return new ResponseEntity<>(noteService.getEvaluationsByStudentClassID(classID), HttpStatus.OK);
    }
}
