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
    public StudentiServiceImplementation(StudentRepository repository){
        this.repository = repository;
    }

    @Override
    public Iterable<Student> getALLStudents() {
        var students = repository.findAll();
        return students;
    }

    @Override
    public Optional<Student> findStudentById(long id) {
        var student1 = repository.findById(id);
        return student1;
    }

    @Override
    public Student create(Student s) {
        var student = repository.save(s);
        return student;
    }
}
