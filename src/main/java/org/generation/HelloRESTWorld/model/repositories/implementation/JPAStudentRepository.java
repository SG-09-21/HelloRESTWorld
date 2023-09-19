package org.generation.HelloRESTWorld.model.repositories.implementation;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.generation.HelloRESTWorld.model.Student;
import org.generation.HelloRESTWorld.model.repositories.abstraction.StudentRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("orm")
public class JPAStudentRepository implements StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> findAll() {
        var students = entityManager.createQuery("from Student", Student.class).getResultList();
        //crea quary con entity manager 'JPQL' crea una quary dal nome della classe
        return students;
    }

    @Override
    public Optional<Student> findById(long id) {
        return Optional.ofNullable(entityManager.find(Student.class, id));
        /*
        Student s = entityManager.find(Student.class, id);
        if (s != null) {
            return Optional.of(s);
        }
        return Optional.empty();
         */
    }

    @Override
    public Student save(Student s) {
        return entityManager.merge(s);
    }
}
