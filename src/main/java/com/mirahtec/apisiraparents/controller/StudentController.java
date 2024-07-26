package com.mirahtec.apisiraparents.controller;

import com.mirahtec.apisiraparents.model.Student;
import com.mirahtec.apisiraparents.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/parent/{parentUsername}")
    public ResponseEntity<?> getStudentsByParentId(@PathVariable String parentUsername) {
        try {
            List<Student> students = studentService.getStudentsByParentUsername(parentUsername);
            if (students.isEmpty()) {
                HashMap<String, String> response = new HashMap<>();

                response.put("status", "false");
                response.put("message", "Parent not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            HashMap<String, String> response = new HashMap<>();
            response.put("status", "false");
            response.put("message","Internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
