package com.amigoscode.project.controller;

import com.amigoscode.project.entity.Student;
import com.amigoscode.project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class Controller {
    private final StudentService service;

    @Autowired
    public Controller(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Student> getStudents(){
        return service.getStudents();
    }


    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        service.addNewStudent(student);
    }

    @DeleteMapping(path="{studentId}")
    @Transactional
    public void deleteStudent(@PathVariable ("studentId") Long id){
        service.deleteById(id);
    }

    @PutMapping
    @Transactional
    public void updateStudent(@RequestBody Student student){
        service.updateById(student.getId(),student);
    }

}
