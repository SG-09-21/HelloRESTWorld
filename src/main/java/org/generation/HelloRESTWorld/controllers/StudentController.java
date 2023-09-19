package org.generation.HelloRESTWorld.controllers;

import org.generation.HelloRESTWorld.model.Student;
import org.generation.HelloRESTWorld.model.services.abstraction.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@RestController
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController (StudentService studentService){
        this.studentService = studentService;
    }
    @GetMapping(value = "/student") //quando viene invocato il metodo
    public Iterable<Student> getAllStudent() {
        var students = studentService.getALLStudents();
        return students;
    }

}
