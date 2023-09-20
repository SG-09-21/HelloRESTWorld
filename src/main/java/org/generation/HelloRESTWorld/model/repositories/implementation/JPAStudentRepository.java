package org.generation.HelloRESTWorld.model.repositories.implementation;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.generation.HelloRESTWorld.model.Student;
import org.generation.HelloRESTWorld.model.repositories.abstraction.StudentRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("orm")
public class JPAStudentRepository implements StudentRepository {

    @PersistenceContext
    private EntityManager entityManager; // L'EntityManager gestisce l'interazione con il database

    // Implementazione del metodo per ottenere tutti gli studenti
    @Override
    public List<Student> findAll() {
        // Utilizza l'EntityManager per creare una query JPQL per ottenere tutti gli studenti
        var students = entityManager.createQuery("from Student", Student.class).getResultList();
        return students;
    }

    // Implementazione del metodo per trovare uno studente per ID
    @Override
    public Optional<Student> findById(long id) {
        // Utilizza l'EntityManager per cercare uno studente per ID utilizzando la classe Student
        return Optional.ofNullable(entityManager.find(Student.class, id));
    }

    // Implementazione del metodo per salvare uno studente
    @Override
    public Student save(Student s) {
        // Utilizza l'EntityManager per salvare o aggiornare uno studente nel database
        return entityManager.merge(s);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.getReference(Student.class, id));
    }
}
