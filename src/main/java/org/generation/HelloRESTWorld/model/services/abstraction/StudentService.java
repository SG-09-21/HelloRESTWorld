package org.generation.HelloRESTWorld.model.services.abstraction;

import org.generation.HelloRESTWorld.model.Student;

import java.util.Optional;

public interface StudentService {

    // Metodo per ottenere tutti gli studenti
    Iterable<Student> getALLStudents();

    // Metodo per trovare uno studente per ID
    Optional<Student> findStudentById(long id);

    // Metodo per creare uno studente
    Student create(Student s);
}
