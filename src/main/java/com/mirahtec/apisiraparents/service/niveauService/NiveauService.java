package com.mirahtec.apisiraparents.service;

import com.mirahtec.apisiraparents.dao.niveau.NiveauJDBCDaoImpl;
import com.mirahtec.apisiraparents.model.Class;
import com.mirahtec.apisiraparents.model.Niveau;
import com.mirahtec.apisiraparents.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NiveauService {
    @Autowired
    private NiveauJDBCDaoImpl niveauJDBCDao;
    @Autowired
    private ClassService classService;
    @Autowired
    private StudentService studentService;

    public Niveau getNiveauByMatricule(String matricule) {
        try {
            log.info("Service presence : Getting niveau for student with matricule: {}", matricule);
            Student student = studentService.getStudentByMatricule(matricule);
            if (student == null) {
                log.info("Student not found with matricule: {}", matricule);
                return null;
            }
            log.info("Student: {}", student);
            Class classe = classService.getClassById(student.getIdClasse());
            if (classe == null) {
                log.info("Class not found with idClasse: {}", student.getIdClasse());
                return null;
            }
            log.info("Class: {}", classe);
            int idNiveau = classe.getIdNiveau();
            Niveau niveau = niveauJDBCDao.getNiveauByIdNiveau(idNiveau);
            log.info("Niveau: {}", niveau);
            return niveau;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
