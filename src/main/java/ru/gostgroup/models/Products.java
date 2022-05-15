package ru.gostgroup.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Products {

    private int id;
    @NotEmpty(message = "Продукт не должен быть пустым")
    @Size(min = 2, max = 60, message = "2-60 символа")
    private String nameProd;
    private int depId;
    private String nameDep;

    public Products() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProd() {
        return nameProd;
    }

    public void setNameProd(String nameProd) {
        this.nameProd = nameProd;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public String getNameDep() {
        return nameDep;
    }

    public void setNameDep(String nameDep) {
        this.nameDep = nameDep;
    }
}
