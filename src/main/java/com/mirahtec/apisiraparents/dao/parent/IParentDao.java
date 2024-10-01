package com.mirahtec.apisiraparents.dao.parent;

import com.mirahtec.apisiraparents.model.Parent;
import com.mirahtec.apisiraparents.model.Student;

import java.util.List;

public interface IParentDao {
    // liste des étudiants dun parent by username
    List<Student> findStudentsByParentUsername(String username);

    Parent findByUsername(String username);

    List<Parent> findAll2(int page, int size);
}
