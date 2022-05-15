package ru.gostgroup.models;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashMap;

public class Employees {

    private int id;

    @NotEmpty(message = "ФИО не должно быть пустым")
    @Size(min = 2, max = 60, message = "2-60 символа")
    private String FIO;


    private int depId;
    private String depName;

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Employees() {
    }

    public Employees(int id, String FIO, int depId, String depName) {
        this.id = id;
        this.FIO = FIO;
        this.depId = depId;
        this.depName = depName;
    }


    public void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }


    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "id=" + id +
                ", FIO='" + FIO + '\'' +
                ", depId=" + depId +
                '}';
    }
}
