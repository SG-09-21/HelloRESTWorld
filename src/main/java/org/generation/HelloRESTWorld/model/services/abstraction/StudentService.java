package org.generation.HelloRESTWorld.model.services.abstraction;

import org.generation.HelloRESTWorld.model.Student;

import java.util.Optional;

public interface StudentService {

    Iterable<Student> getALLStudents();

    Optional<Student> findStudentById(long id);

    Student create(Student s);
}
