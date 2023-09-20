package org.generation.HelloRESTWorld.model.services.implementation;

import org.generation.HelloRESTWorld.model.Student;
import org.generation.HelloRESTWorld.model.repositories.abstraction.StudentRepository;
import org.generation.HelloRESTWorld.model.services.abstraction.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentiServiceImplementation implements StudentService {

    private StudentRepository repository;

    @Autowired
    public StudentiServiceImplementation(StudentRepository repository) {
        this.repository = repository;
    }

    // Implementazione del metodo per ottenere tutti gli studenti
    @Override
    public Iterable<Student> getALLStudents() {
        var students = repository.findAll();
        return students;
    }

    // Implementazione del metodo per trovare uno studente per ID
    @Override
    public Optional<Student> findStudentById(long id) {
        var student1 = repository.findById(id);
        return student1;
    }

    // Implementazione del metodo per creare uno studente
    @Override
    public Student create(Student s) {
        var student = repository.save(s);
        return student;
    }
}
