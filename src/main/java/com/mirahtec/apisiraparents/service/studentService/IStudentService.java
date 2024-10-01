package com.mirahtec.apisiraparents.service.studentService;

import com.mirahtec.apisiraparents.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> getAllStudents();

    Student getStudentById(Long studentId);

    List<Student> getStudentsByParentUsername(String parentUsername);

    Student getStudentByMatricule(String matricule);

    List<Student> getStudentsByMatriculeParent(String matriculeParent);
}
