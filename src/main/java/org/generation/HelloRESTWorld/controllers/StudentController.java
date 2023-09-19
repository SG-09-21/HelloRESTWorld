package org.generation.HelloRESTWorld.controllers;

import org.generation.HelloRESTWorld.dtos.StudentDto;
import org.generation.HelloRESTWorld.model.Student;
import org.generation.HelloRESTWorld.model.services.abstraction.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/student") //quando viene invocato il metodo
    public ResponseEntity<Iterable<StudentDto>> getAllStudent() {
        List<StudentDto> studentDtoList = new ArrayList<>();
        var students = studentService.getALLStudents();
        for (Student student : students) {
            StudentDto studentDto = new StudentDto(student.getId(), student.getFullname());
            studentDtoList.add(studentDto);
        }
        return new ResponseEntity<>(studentDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/student/{id}")
    public ResponseEntity<StudentDto> findByIdDto(@PathVariable long id) {
        Optional<Student> studentOptional = studentService.findStudentById(id);

        if (studentOptional.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Student s = studentOptional.get();
        StudentDto sDto = new StudentDto(s.getId(), s.getFullname());
        return new ResponseEntity<>(sDto, HttpStatus.OK);
    }

}
