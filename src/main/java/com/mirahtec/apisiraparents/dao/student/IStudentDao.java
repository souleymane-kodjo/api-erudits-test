package com.mirahtec.apisiraparents.dao.student;

import com.mirahtec.apisiraparents.model.Student;

import java.util.List;

public interface IStudentDao {
    List<Student> findAll();
    // TODO :  Get student by matricule
    public Student getStudentByMatricule(String matricule);
}
