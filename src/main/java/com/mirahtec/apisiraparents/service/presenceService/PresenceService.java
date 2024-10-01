package com.mirahtec.apisiraparents.service;

import com.mirahtec.apisiraparents.dao.presence.PresenceJDBCDaoImpl;
import com.mirahtec.apisiraparents.model.Niveau;
import com.mirahtec.apisiraparents.model.Presence;
import com.mirahtec.apisiraparents.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PresenceService {
    @Autowired
    private PresenceJDBCDaoImpl presenceJDBCDao;
    @Autowired
    private StudentService studentService;
    @Autowired
    private NiveauService niveauService;

    public List<Presence> getPresenceByMatricule(String matricule) {
        if (matricule == null) {
            log.info("Service presence : Matricule is null");
            return null;
        }
        try {
            Student student = studentService.getStudentByMatricule(matricule);
            if (student == null) {
                log.info("Service presence : Student not found");
                return null;
            }
            log.info("Service presence : Getting presence for student with matricule: {}",student);
            Niveau niveau = niveauService.getNiveauByMatricule(matricule);
            if (niveau == null) {
                log.info("Service presence : Niveau not found");
                return null;
            }
            switch (niveau.getNomNiveau()) {
                case "Moyen":
                    log.info("Presence Niveau Moyen Selected in Service");
                    return presenceJDBCDao.getPresencesMoyenByMatricule(matricule);
                case "Secondaire":
                    log.info("Presence Niveau Secondaire Selected in Service");
                    return presenceJDBCDao.getPresencesSecondaireByMatricule(matricule);
                default:
                    log.info("Presence Niveau not found");
                    return presenceJDBCDao.getPresencesByMatricule(matricule);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
