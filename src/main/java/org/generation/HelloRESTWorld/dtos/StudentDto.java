package org.generation.HelloRESTWorld.dtos;

public class StudentDto {

    private long id;
    private String fullName;

    public StudentDto(long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public StudentDto() {
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }
}
