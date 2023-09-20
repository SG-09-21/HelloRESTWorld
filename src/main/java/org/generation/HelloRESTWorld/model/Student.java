package org.generation.HelloRESTWorld.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tipo di generazione dell'ID
    private long id;

    @Column(name = "firstname") // Nome della colonna nel database per il campo "firstName"
    private String firstName;

    @Column(name = "lastname") // Nome della colonna nel database per il campo "lastName"
    private String lastName;

    private LocalDate birthdate; // Data di nascita dello studente

    // Costruttore per inizializzare gli attributi
    public Student(long id, String firstName, String lastName, LocalDate birthdate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
    }

    // Costruttore vuoto
    public Student(){
    }

    // Metodo per ottenere il nome completo dello studente
    public String getFullname(){
        return getFirstName() + " " + getLastName();
    }

    // Metodi getter e setter per gli attributi
    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
