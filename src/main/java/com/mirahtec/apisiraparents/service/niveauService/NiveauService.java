package com.mirahtec.apisiraparents.service.niveauService;

import com.mirahtec.apisiraparents.dao.niveau.NiveauJDBCDaoImpl;
import com.mirahtec.apisiraparents.model.Class;
import com.mirahtec.apisiraparents.model.Niveau;
import com.mirahtec.apisiraparents.model.Student;
import com.mirahtec.apisiraparents.service.studentService.StudentService;
import com.mirahtec.apisiraparents.service.classService.ClassService;
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
            Student student = studentService.getStudentByMatricule(matricule);
            if (student == null) {
                return null;
            }
            Class classe = classService.getClassById(student.getIdClasse());
            if (classe == null) {
                return null;
            }
            int idNiveau = classe.getIdNiveau();
            Niveau niveau = niveauJDBCDao.getNiveauByIdNiveau(idNiveau);
            return niveau;
        } catch (Exception e) {
            log.error("Service presence : Error getting niveau for student with matricule: {}", matricule);
        }
        return null;
    }
}