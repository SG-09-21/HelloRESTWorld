package org.generation.HelloRESTWorld.model.repositories.abstraction;

import org.generation.HelloRESTWorld.model.Student;

import java.util.Optional;

public interface StudentRepository {

    Iterable<Student> getALLStudents();

    Optional<Student> findStudentById(long id);

    Student create(Student s);


}
