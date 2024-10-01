package com.mirahtec.apisiraparents.service.studentService;

import com.mirahtec.apisiraparents.dao.parent.ParentJDBCDaoImpl;
import com.mirahtec.apisiraparents.dao.student.StudentJDBCDaoImpl;
import com.mirahtec.apisiraparents.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService {
    private static final Logger log = LoggerFactory.getLogger(StudentService.class);
    @Autowired
    StudentJDBCDaoImpl studentJDBCDao;

    @Autowired
    ParentJDBCDaoImpl parentJDBCDao;

    
    @Override
    public List<Student> getAllStudents() {
        return studentJDBCDao.findAll();
    }
    @Override
    public Student getStudentById(Long studentId) {
        return null;
    }
    @Override
    public List<Student> getStudentsByParentUsername(String parentUsername) {
        try {

            List<Student> students = parentJDBCDao.findStudentsByParentUsername(parentUsername);
            return students;
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Error while fetching students for parent with username: {}", parentUsername);
            return null;
        }
    }
    @Override
    public Student getStudentByMatricule(String matricule) {
        return studentJDBCDao.getStudentByMatricule(matricule);
    }
    @Override
    public List<Student> getStudentsByMatriculeParent(String matriculeParent) {
        return studentJDBCDao.findStudentsByMatriculeParent(matriculeParent);
    }
}
