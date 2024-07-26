package com.mirahtec.apisiraparents.controller;

import com.mirahtec.apisiraparents.dao.impl.ParentJDBCDaoImpl;
import com.mirahtec.apisiraparents.model.Parent;
import com.mirahtec.apisiraparents.model.Student;
import com.mirahtec.apisiraparents.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/parents")
@Slf4j
public class ParentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ParentJDBCDaoImpl parentJDBCDao ;
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }


    @GetMapping
    public ResponseEntity<List<Parent>> getAllParents() {
        try {
            List<Parent> parents = parentJDBCDao.findAll();
            return ResponseEntity.ok(parents);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }

}
