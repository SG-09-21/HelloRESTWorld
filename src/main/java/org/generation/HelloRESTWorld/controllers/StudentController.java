package org.generation.HelloRESTWorld.controllers;

import org.generation.HelloRESTWorld.dtos.StudentDto;
import org.generation.HelloRESTWorld.model.Student;
import org.generation.HelloRESTWorld.model.services.abstraction.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    // Metodo per ottenere tutti gli studenti
    @GetMapping(value = "/student")
    public ResponseEntity<Iterable<StudentDto>> getAllStudent() {
        List<StudentDto> studentDtoList = new ArrayList<>();

        // Ottenere la lista di tutti gli studenti dal servizio
        var students = studentService.getALLStudents();

        // Iterare su ciascuno studente e creare un DTO per ciascuno
        for (Student student : students) {
            StudentDto studentDto = new StudentDto(student.getId(), student.getFullname());
            studentDtoList.add(studentDto);
        }

        // Restituire la lista di DTO degli studenti con uno status HTTP OK
        return new ResponseEntity<>(studentDtoList, HttpStatus.OK);
    }

    // Metodo per trovare uno studente per ID
    @GetMapping(value = "/student/{id}")
    public ResponseEntity<StudentDto> findByIdDto(@PathVariable long id) {
        // Trovare uno studente per ID utilizzando il servizio
        Optional<Student> studentOptional = studentService.findStudentById(id);

        // Verificare se lo studente è stato trovato o no
        if (studentOptional.isEmpty()) {
            // Se non è stato trovato, restituire uno status HTTP NOT FOUND
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        // Se lo studente è stato trovato, creare un DTO per lo studente e restituirlo con uno status HTTP OK
        Student s = studentOptional.get();
        StudentDto sDto = new StudentDto(s.getId(), s.getFullname());
        return new ResponseEntity<>(sDto, HttpStatus.OK);
    }

    // Metodo per creare un nuovo studente

    @PostMapping(value = "/student")
    public ResponseEntity<StudentDto> createStudentDto(StudentDto studentDto){
        Student s = studentDto.toStudent();
        studentService.create(s);
        StudentDto sDto = new StudentDto(s);
        return new ResponseEntity<>(sDto, HttpStatus.CREATED);
    }
}
