package com.mirahtec.apisiraparents.controller.note;

import com.mirahtec.apisiraparents.service.noteService.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notes")
@Slf4j
public class NoteController {
    @Autowired
    private NoteService noteService;
    @GetMapping("/student/{matricule}")
    public ResponseEntity<?> getNotesByStudentMatricule(@PathVariable String matricule) {
        log.info("Note service called: "+matricule);
        if (matricule == null || matricule.isEmpty()) {
            log.error("Matricule is required");
            return new ResponseEntity<>("Matricule is required", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(noteService.getNotesByStudentMatricule(matricule), HttpStatus.OK);
    }
}
