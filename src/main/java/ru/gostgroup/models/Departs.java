package ru.gostgroup.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Departs {

    private int id;

    @NotEmpty(message = "Департамент не должен быть пустым")
    @Size(min = 2, max = 60, message = "2-60 символа")
    private String name;

    public Departs() {
    }

    public Departs(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Depart{" +
                "id=" + id +
                ", depName='" + name + '\'' +
                '}';
    }
}
