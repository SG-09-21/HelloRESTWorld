package org.generation.HelloRESTWorld.model.repositories.abstraction;

import org.generation.HelloRESTWorld.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    List<Student> findAll();

    Optional<Student> findById(long id);

    Student save(Student s);


}
