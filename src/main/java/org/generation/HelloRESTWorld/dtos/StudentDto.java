package org.generation.HelloRESTWorld.dtos;

import org.generation.HelloRESTWorld.model.Student;

import java.time.LocalDate;

public class StudentDto {

    private long id; // ID dello studente
    private String fullName; // Nome completo dello studente

    // Costruttore per inizializzare gli attributi
    public StudentDto(long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    // Costruttore vuoto
    public StudentDto() {
    }

    public StudentDto(Student s) {
        this.id = s.getId();
        this.fullName = s.getFullname();
    }

    // Metodo getter per ottenere l'ID dello studente
    public long getId() {
        return id;
    }

    // Metodo getter per ottenere il nome completo dello studente
    public String getFullName() {
        return fullName;
    }

    public Student toStudent() {
        String[] tokens = fullName.split(" ");
        Student s = new Student(0, tokens[0], tokens[1], LocalDate.of(1000, 1, 1));
        return s;
    }
}
