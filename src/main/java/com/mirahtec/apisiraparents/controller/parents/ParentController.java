package com.mirahtec.apisiraparents.controller.parents;

import com.mirahtec.apisiraparents.dao.parent.ParentJDBCDaoImpl;
import com.mirahtec.apisiraparents.model.Parent;
import com.mirahtec.apisiraparents.model.Student;
import com.mirahtec.apisiraparents.service.parent.ParentService;
import com.mirahtec.apisiraparents.service.studentService.IStudentService;
import com.mirahtec.apisiraparents.service.studentService.StudentService;
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
    private IStudentService studentService;
    @Autowired
    private ParentService parentService;
    @Autowired
    private ParentJDBCDaoImpl parentJDBCDao ;

    @GetMapping("/{parentUsername}")
    public Parent getParentByUsername(@PathVariable String parentUsername) {
        log.info("Parent username: {}", parentUsername);
        return parentJDBCDao.findByUsername(parentUsername);
    }
//    @GetMapping("/{parentUsername}/students")
//    public ResponseEntity<?> getStudentsByParentUsername(@PathVariable String parentUsername) {
//        String matriculeParent = parentService.getMatriculeParentByParentUsername(parentUsername);
//        List<Student> students = studentService.getStudentsByMatriculeParent(matriculeParent);
//        return ResponseEntity.ok(students);
//    }
    @GetMapping("/{parentUsername}/students")
    public ResponseEntity<?> getStudentsByParentUsername(@PathVariable String parentUsername) {
            String matriculeParent = parentService.getMatriculeParentByParentUsername(parentUsername);
            List<Student> students = studentService.getStudentsByMatriculeParent(matriculeParent);
            return ResponseEntity.ok(students);
    }


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
    @GetMapping("/page")
    public ResponseEntity<List<Parent>> getAllParents2(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        try {
            List<Parent> parents = parentJDBCDao.findAll2(page, size);
            return ResponseEntity.ok(parents);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }

}
