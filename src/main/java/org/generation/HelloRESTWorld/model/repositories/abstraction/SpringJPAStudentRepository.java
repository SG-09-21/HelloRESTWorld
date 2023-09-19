package org.generation.HelloRESTWorld.model.repositories.abstraction;

import org.generation.HelloRESTWorld.model.Student;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("spring")
public interface SpringJPAStudentRepository extends StudentRepository, JpaRepository<Student,Long>{}
