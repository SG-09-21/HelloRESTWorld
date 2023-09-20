package org.generation.HelloRESTWorld.model.repositories.abstraction;

import org.generation.HelloRESTWorld.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    // Metodo per ottenere una lista di tutti gli studenti
    List<Student> findAll();

    // Metodo per trovare uno studente per ID
    Optional<Student> findById(long id);

    // Metodo per salvare uno studente
    Student save(Student s);

    void deleteById(Long id);
}
