package com.mirahtec.apisiraparents.service;

import com.mirahtec.apisiraparents.dao.parent.ParentJDBCDaoImpl;
import com.mirahtec.apisiraparents.dao.student.StudentJDBCDaoImpl;
import com.mirahtec.apisiraparents.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private static final Logger log = LoggerFactory.getLogger(StudentService.class);
    @Autowired
    StudentJDBCDaoImpl studentJDBCDao;

    @Autowired
    ParentJDBCDaoImpl parentJDBCDao;

    
    public List<Student> getAllStudents() {
        return studentJDBCDao.findAll();
    }
    public Student getStudentById(Long studentId) {
        return null;
    }


    public List<Student> getStudentsByParentUsername(String parentUsername) {
        try {
            //SELECT eleves23.matricule, eleves23.nomEleve, eleves23.prenomEleve, eleves23.dateNaissanceEleve as ddn, eleves23.lieuN as ldn, classes.nom_classe FROM  parents  INNER JOIN eleves23 ON eleves23.matricule=parents.matriculeEleve INNER JOIN classes ON eleves23.idClasse=classes.idClasse where parents.telephone ='775119807'
            List<Student> students = parentJDBCDao.findStudentsByParentUsername(parentUsername);
            return students;
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Error while fetching students for parent with username: {}", parentUsername);
            return null;
        }
    }

    public Student getStudentByMatricule(String matricule) {
        return studentJDBCDao.getStudentByMatricule(matricule);
    }
}
