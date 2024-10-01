package com.mirahtec.apisiraparents.controller.presence;

import com.mirahtec.apisiraparents.model.Presence;
import com.mirahtec.apisiraparents.service.presenceService.PresenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/presences")
@Slf4j
public class PresenceController {
    @Autowired
    private PresenceService presenceService;
    @RequestMapping("/student/{matricule}")
    public ResponseEntity<?> getPresenceByMatricule(@PathVariable String matricule){
        log.info("Getting presence of student with matricule: "+matricule);
        List<Presence> presences = presenceService.getPresenceByMatricule(matricule);
        if(matricule.isEmpty()){
            HashMap<String, String> response = new HashMap<>();
            response.put("status", "false");
            response.put("message", "Matricule empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        if(matricule == null){
            HashMap<String, String> response = new HashMap<>();
            response.put("status", "false");
            response.put("message", "Matricule null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        HashMap<String, String> response = new HashMap<>();
        if (presences == null) {
            response.put("status", "false");
            response.put("message", "Presence null");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        if (presences.isEmpty()  )
        {
            response.put("status", "false");
            response.put("message", "Liste de presence vide");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        if (presences.size() == 0) {
            response.put("status", "false");
            response.put("message", "Liste de presence vide");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
        return ResponseEntity.ok(presences);
    }
}
