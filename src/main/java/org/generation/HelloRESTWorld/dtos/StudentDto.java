package org.generation.HelloRESTWorld.dtos;

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

    // Metodo getter per ottenere l'ID dello studente
    public long getId() {
        return id;
    }

    // Metodo getter per ottenere il nome completo dello studente
    public String getFullName() {
        return fullName;
    }
}
